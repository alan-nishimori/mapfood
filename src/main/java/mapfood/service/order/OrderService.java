package mapfood.service.order;

import mapfood.dto.order.OrderDto;
import mapfood.model.order.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto) throws RuntimeException;

    OrderDto update(String id, OrderStatus orderStatus);

    OrderDto findById(String id);

    List<OrderDto> findAllByClientId(Integer id);

    List<OrderDto> findAllByEstablishmentId(String id);

    List<OrderDto> findAllByEstablishmentIdAndDateBetween(String id, Instant start, Instant end);

    List<OrderDto> findAllByEstablishmentIdAndDateWithStatus(String id, Instant date, OrderStatus orderStatus);
}
