package mapfood.repository.order;

import mapfood.model.order.Order;
import mapfood.model.order.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByClientId(Integer id);

    List<Order> findAllByEstablishmentId(String id);

    List<Order> findAllByEstablishmentIdAndCreatedAtBetween(String id, Instant start, Instant end);

    List<Order> findAllByEstablishmentIdAndCreatedAtBeforeAndOrderStatus(String id, Instant date, OrderStatus orderStatus);
}
