package mapfood.dto.delivery;

import mapfood.dto.google.maps.api.Route;
import mapfood.dto.motoboy.MotoboyDto;
import mapfood.dto.order.OrderDto;
import mapfood.model.delivery.DeliveryStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDto {

    private String id;

    private final List<OrderDto> orders = new ArrayList<>();

    private MotoboyDto motoboyDto;

    private final List<Route> routes = new ArrayList<>();

    private Instant createdAt;

    private Instant updatedAt;

    private DeliveryStatus status;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void addOrder(final OrderDto orderDto) {
        this.orders.add(orderDto);
    }

    public MotoboyDto getMotoboyDto() {
        return motoboyDto;
    }

    public void setMotoboyDto(final MotoboyDto motoboyDto) {
        this.motoboyDto = motoboyDto;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(final Route route) {
        this.routes.add(route);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(final DeliveryStatus status) {
        this.status = status;
    }
}
