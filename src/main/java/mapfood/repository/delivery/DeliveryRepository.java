package mapfood.repository.delivery;

import mapfood.model.delivery.Delivery;
import mapfood.model.delivery.DeliveryStatus;
import mapfood.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    List<Delivery> findAllByCreatedAtBeforeAndStatus(Instant now, DeliveryStatus deliveryStatus);

    List<Delivery> findAllByMotoboy_Id(Integer id);

    Optional<Delivery> findFirstByMotoboy_IdAndStatusOrderByCreatedAtDesc(Integer id, DeliveryStatus status);
}
