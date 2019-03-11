package mapfood.converter.order;

import mapfood.converter.product.ProductEntityToDto;
import mapfood.dto.order.OrderDto;
import mapfood.model.order.Order;

public class OrderEntityToDto {

    private final Order order;

    public OrderEntityToDto(final Order order) {
        this.order = order;
    }

    public OrderDto build() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setClientId(order.getClientId());
        orderDto.setEstablishmentId(order.getEstablishmentId());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setValue(order.getValue());
        orderDto.setCreatedAt(order.getCreatedAt());

        order.getProducts().forEach(product -> orderDto.addProduct(new ProductEntityToDto(product).build()));

        return orderDto;
    }
}
