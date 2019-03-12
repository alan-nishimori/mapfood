package mapfood.service.order.impl;

import mapfood.converter.order.OrderEntityToDto;
import mapfood.dto.establishment.product.ProductDto;
import mapfood.dto.order.OrderDto;
import mapfood.facade.delivery.DeliveryOrchestrator;
import mapfood.model.client.Client;
import mapfood.model.establishment.Establishment;
import mapfood.model.establishment.product.Product;
import mapfood.model.order.Order;
import mapfood.model.order.OrderStatus;
import mapfood.repository.client.ClientRepository;
import mapfood.repository.establishment.EstablishmentRepository;
import mapfood.repository.order.OrderRepository;
import mapfood.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    DeliveryOrchestrator deliveryOrchestrator;


    @Override
    public OrderDto save(final OrderDto orderDto) throws RuntimeException{
        final Optional<Client> client = clientRepository.findById(orderDto.getClientId());
        if (!client.isPresent()) {
            throw new RuntimeException("No client found with id: " + orderDto.getClientId());
        }

        final Optional<Establishment> establishment = establishmentRepository.findById(orderDto.getEstablishmentId());
        if (!establishment.isPresent()) {
            throw new RuntimeException("No establishment found with id: " + orderDto.getEstablishmentId());
        }

        Order order = new Order();
        order.setClientId(orderDto.getClientId());
        order.setEstablishmentId(orderDto.getEstablishmentId());
        order.setId(order.getEstablishmentId() + order.getClientId().toString() + order.getCreatedAt().toString());

        double total = 0;

        final List<String> productsId = orderDto.getProductsId();
        for (int i = 0; i < productsId.size(); i++) {

            final Product product = establishment.get().getProductById(productsId.get(i));

            if (Objects.isNull(product)) {
                throw new RuntimeException("Product with id: " + productsId.get(i) + " not found");
            }

            total += product.getPrice();
            order.addProduct(product);
        }
        order.setValue(total);

        order = orderRepository.save(order);

        deliveryOrchestrator.deliveryCreator(order);

        return new OrderEntityToDto(order).build();
    }

    @Override
    public OrderDto update(final String id, final OrderStatus orderStatus) {
        final Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            order.get().setOrderStatus(orderStatus);
            return new OrderEntityToDto(orderRepository.save(order.get())).build();
        }

        return null;
    }

    @Override
    public OrderDto findById(final String id) {
        final Optional<Order> order = orderRepository.findById(id);

        return order.map(order1 -> new OrderEntityToDto(order1).build()).orElse(null);
    }

    @Override
    public List<OrderDto> findAllByClientId(final Integer id) {
        final List<Order> orders = orderRepository.findAllByClientId(id);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentId(final String id) {
        final List<Order> orders = orderRepository.findAllByEstablishmentId(id);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentIdAndDateBetween(final String id, final Instant start, final Instant end) {
        final List<Order> orders = orderRepository.findAllByEstablishmentIdAndCreatedAtBetween(id, start, end);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentIdAndDateWithStatus(
            final String id, final Instant date, final OrderStatus orderStatus) {
        final List<Order> orders = orderRepository.findAllByEstablishmentIdAndCreatedAtBeforeAndOrderStatus(id, date, orderStatus);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }
}
