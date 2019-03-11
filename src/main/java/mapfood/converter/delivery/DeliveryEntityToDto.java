package mapfood.converter.delivery;

import mapfood.converter.motoboy.MotoboyEntityToDto;
import mapfood.converter.order.OrderEntityToDto;
import mapfood.dto.delivery.DeliveryDto;
import mapfood.model.delivery.Delivery;

public class DeliveryEntityToDto {

    private final Delivery delivery;

    public DeliveryEntityToDto(final Delivery delivery) {
        this.delivery = delivery;
    }

    public DeliveryDto build() {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setCreatedAt(delivery.getCreatedAt());
        deliveryDto.setUpdatedAt(delivery.getUpdatedAt());
        delivery.getRoutes().forEach(r -> deliveryDto.addRoute(r));
        deliveryDto.setStatus(delivery.getStatus());

        deliveryDto.setMotoboyDto(new MotoboyEntityToDto(delivery.getMotoboy()).build());
        delivery.getOrders().forEach(order -> deliveryDto.addOrder(new OrderEntityToDto(order).build()));

        return deliveryDto;
    }
}
