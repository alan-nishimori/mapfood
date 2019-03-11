package mapfood.repository.order;

import mapfood.model.order.Order;
import mapfood.model.order.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Integer> {

    List<Order> findAllByClientId(Integer id);

    List<Order> findAllByEstablishmentId(String id);

    List<Order> findAllByEstablishmentIdAndCreatedAtBetween(String id, Date start, Date end);

    List<Order> findAllByEstablishmentIdAndCreatedAtAndOrderStatus(String id, Date date, OrderStatus orderStatus);
}
