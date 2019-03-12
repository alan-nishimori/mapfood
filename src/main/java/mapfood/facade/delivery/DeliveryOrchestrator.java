package mapfood.facade.delivery;

import mapfood.model.delivery.Delivery;
import mapfood.model.order.Order;
import mapfood.model.order.OrderStatus;
import mapfood.repository.delivery.DeliveryRepository;
import mapfood.repository.order.OrderRepository;
import mapfood.service.delivery.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class DeliveryOrchestrator {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryService deliveryService;

    public void deliveryCreator(final Order order) {
        final List<Order> orders = orderRepository.findAllByEstablishmentIdAndCreatedAtBeforeAndOrderStatus(
                order.getEstablishmentId(), Instant.now(), OrderStatus.PREPARING);

        if (1 == orders.size()) {
            deliveryService.save(order);
        } else {
            final Optional<Delivery> delivery = deliveryRepository.findByOrdersContaining(orders.get(0));
            if (delivery.isPresent() && delivery.get().getOrders().size() <= 5) {
                deliveryService.addOrder(delivery.get().getId(), order);
            } else {
                deliveryService.save(order);
            }
        }
    }
}
