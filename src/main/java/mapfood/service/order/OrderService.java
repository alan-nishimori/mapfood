package mapfood.service.order;

import mapfood.dto.order.OrderDto;
import mapfood.model.order.OrderStatus;

import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto) throws RuntimeException;

    OrderDto update(Integer id, OrderStatus orderStatus);

    OrderDto findById(Integer id);

    List<OrderDto> findAllByClientId(Integer id);

    List<OrderDto> findAllByEstablishmentId(String id);

    List<OrderDto> findAllByEstablishmentIdAndDateBetween(String id, Date start, Date end);

    List<OrderDto> findAllByEstablishmentIdAndDateWithStatus(String id, Date date, OrderStatus orderStatus);
}
