package mapfood.service.delivery;

import mapfood.dto.delivery.DeliveryDto;
import mapfood.model.delivery.DeliveryStatus;
import mapfood.model.order.Order;

import java.util.List;
import java.util.Map;

public interface DeliveryService {

    void save(Order order) throws RuntimeException;

    void addOrder(String id, Order order) throws RuntimeException;

    DeliveryDto findById(String id);

    List<DeliveryDto> findByMotoboy(Integer id);

    DeliveryDto findDeliveryNotFinishedByMotoboy(Integer id);

    DeliveryDto findByOrder(String id) throws RuntimeException;

    DeliveryDto updateStatus(String id, DeliveryStatus deliveryStatus);

    Map<String, List<String>> getRoute(String id);
}
