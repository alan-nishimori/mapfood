package mapfood.model.delivery;

import mapfood.dto.google.maps.api.Route;
import mapfood.model.motoboy.Motoboy;
import mapfood.model.order.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "delivery")
public class Delivery {

    @Id
    // motoboyid + createdAt
    private String id;

    @Indexed
    private final List<Order> orders = new ArrayList<>();

    @Indexed
    private Motoboy motoboy;

    // index 0 = motoboy to establishment | index 1 = establishment to clients
    private final List<Route> routes = new ArrayList<>();

    private final Instant createdAt = Instant.now();

    private Instant updatedAt;

    private DeliveryStatus status = DeliveryStatus.OPEN;

    private List<GeoJsonPoint> clientsLocation = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(final Order order) {
        this.orders.add(order);
    }

    public Motoboy getMotoboy() {
        return motoboy;
    }

    public void setMotoboy(final Motoboy motoboy) {
        this.motoboy = motoboy;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(final Route route) {
        this.routes.add(route);
    }

    public void updateDeliveryRoute(final Route route) {
        this.routes.set(1, route);
    }

    public Instant getCreatedAt() {
        return createdAt;
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

    public List<GeoJsonPoint> getClientsLocation() {
        return clientsLocation;
    }

    public void addClientLocation(GeoJsonPoint clientLocation) {
        this.clientsLocation.add(clientLocation);
    }
}
