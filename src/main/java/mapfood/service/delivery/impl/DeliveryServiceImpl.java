package mapfood.service.delivery.impl;

import mapfood.converter.delivery.DeliveryEntityToDto;
import mapfood.converter.motoboy.MotoboyWithDistanceToMotoboy;
import mapfood.dto.delivery.DeliveryDto;
import mapfood.model.client.Client;
import mapfood.model.delivery.Delivery;
import mapfood.model.delivery.DeliveryStatus;
import mapfood.model.establishment.Establishment;
import mapfood.model.motoboy.Motoboy;
import mapfood.model.motoboy.MotoboyStatus;
import mapfood.model.motoboy.MotoboyWithDistance;
import mapfood.model.order.Order;
import mapfood.repository.client.ClientRepository;
import mapfood.repository.delivery.DeliveryRepository;
import mapfood.repository.establishment.EstablishmentRepository;
import mapfood.repository.motoboy.MotoboyRepository;
import mapfood.repository.order.OrderRepository;
import mapfood.service.delivery.DeliveryService;
import mapfood.service.gmaps.DirectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DirectionsService directionsService;

    private final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Override
    public void save(final Order order) throws RuntimeException{
        logger.info("Starting delivery creation for order: {}", order);

        Delivery delivery = new Delivery();

        final Establishment establishment = getEstablishmentById(order.getEstablishmentId());

        final Client client = getClientById(order.getClientId());

        final List<MotoboyWithDistance> motoboys = motoboyRepository.findClosestMotoboy(establishment.getLocation());

        final Optional<MotoboyWithDistance> motoboy = motoboys.stream()
                .findFirst().filter(m -> m.getMotoboyStatus() == MotoboyStatus.AVAILABLE);

        if (!motoboy.isPresent()) {
            logger.warn("No motoboy available at the moment to delivery.");
            throw new RuntimeException("No motoboy available to delivery");
        }

        final Motoboy motoboyEntity = new MotoboyWithDistanceToMotoboy(motoboy.get()).build();
        delivery.setMotoboy(motoboyEntity);
        delivery.setId(motoboy.get().getId().toString() + delivery.getCreatedAt().toString());
        motoboyEntity.setMotoboyStatus(MotoboyStatus.WAITING);

        try {
            delivery.addRoute(directionsService
                    .getDistance(motoboy.get().getLocation(), establishment.getLocation(), null).getRoute());
            delivery.addRoute(directionsService
                    .getDistance(establishment.getLocation(), client.getLocation(), null).getRoute());
        } catch (final Exception e) {
            logger.error("Error retrieving route.");
            throw new RuntimeException("Error retrieving route.");
        }

        delivery.addClientLocation(client.getLocation());
        delivery.addOrder(order);

        deliveryRepository.save(delivery);
        motoboyRepository.save(motoboyEntity);
        logger.info("Delivery successfully saved.");
    }

    @Override
    public void addOrder(final String id, final Order order) throws RuntimeException {
        logger.info("Adding new order to delivery with id: {}", id);
        final Optional<Delivery> delivery = deliveryRepository.findById(id);

        if (!delivery.isPresent()) {
            logger.warn("No delivery found with id: {}", id);
            throw new RuntimeException("No delivery found with id: " + id);
        }

        final Client client = getClientById(order.getClientId());
        delivery.get().addClientLocation(client.getLocation());

        final double establishmentLongitude = delivery.get().getRoutes().get(1).legs.get(0).startLocation.lng;
        final double establishmentLatitude = delivery.get().getRoutes().get(1).legs.get(0).startLocation.lat;
        final GeoJsonPoint establishmentLocation = new GeoJsonPoint(establishmentLongitude, establishmentLatitude);

        try {
            final List<GeoJsonPoint> waypoints = new ArrayList<>();
            for (int i = 1; i < delivery.get().getClientsLocation().size(); i++) {
                waypoints.add(delivery.get().getClientsLocation().get(i));
            }
            delivery.get().updateDeliveryRoute(directionsService
            .getDistance(establishmentLocation, delivery.get().getClientsLocation().get(0), waypoints).getRoute());
        } catch (final Exception e) {
            logger.error("Error retrieving route.");
            throw new RuntimeException("Error retrieving route.");
        }

        order.setOrderStatus(null);
        delivery.get().addOrder(order);
        delivery.get().setUpdatedAt(Instant.now());
        deliveryRepository.save(delivery.get());
        logger.info("Order with id: " + order.getId() + " successfully added to delivery: " + delivery.get().getId());
    }

    @Override
    public DeliveryDto findById(final String id) {
        final Optional<Delivery> delivery = deliveryRepository.findById(id);

        if (delivery.isPresent()) {
            logger.info("Successfully retrieved delivery with id: {}", id);
            return new DeliveryEntityToDto(delivery.get()).build();
        }

        return null;
    }

    @Override
    public List<DeliveryDto> findByMotoboy(final Integer id) {
        final List<Delivery> deliveries = deliveryRepository.findAllByMotoboy_Id(id);
        final List<DeliveryDto> deliveriesDto = new ArrayList<>();

        deliveries.forEach(delivery -> deliveriesDto.add(new DeliveryEntityToDto(delivery).build()));
        return  deliveriesDto;
    }

    @Override
    public DeliveryDto findDeliveryNotFinishedByMotoboy(final Integer id) {
        final Optional<Delivery> delivery = deliveryRepository
                .findFirstByMotoboy_IdAndStatusOrderByCreatedAtDesc(id, DeliveryStatus.OPEN);

        return delivery.map(delivery1 -> new DeliveryEntityToDto(delivery1).build()).orElse(null);
    }

    @Override
    public DeliveryDto findByOrder(final String id) throws RuntimeException {
        Optional<Order> order = orderRepository.findById(id);

        if (!order.isPresent()) {
            throw new RuntimeException("Order not found for id: " + id);
        }

        final List<Delivery> deliveries = deliveryRepository.findAllByCreatedAtBeforeAndStatus(Instant.now(), DeliveryStatus.OPEN);

        for (Delivery delivery : deliveries) {
            for (Order deliveryOrder : delivery.getOrders()) {
                if (deliveryOrder.getId().equals(id)) {
                    return new DeliveryEntityToDto(delivery).build();
                }
            }
        }

        return null;
    }

    private Establishment getEstablishmentById(final String id) {
        final Optional<Establishment> establishment = establishmentRepository.findById(id);

        if (!establishment.isPresent()) {
            logger.warn("No establishment found for id: {}", id);
            throw new RuntimeException("Establishment not found with id: " + id);

        }
        return establishment.get();
    }

    private Client getClientById(final Integer id) {
        final Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            logger.warn("No client found for id: {}", id);
            throw new RuntimeException("Client not found with id: " + id);

        }
        return client.get();
    }
}
