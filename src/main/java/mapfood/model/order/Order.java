package mapfood.model.order;

import mapfood.model.establishment.product.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "order")
public class Order {

    @Id
    // establishmentid + client id + createdAt
    private String id;

    private Instant createdAt = Instant.now();

    @Indexed
    private String establishmentId;

    @Indexed
    private Integer clientId;

    private Double value;

    private List<Product> products = new ArrayList<>();

    private OrderStatus orderStatus = OrderStatus.PREPARING;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(String establishmentId) {
        this.establishmentId = establishmentId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", establishmentId='" + establishmentId + '\'' +
                ", clientId=" + clientId +
                ", value=" + value +
                ", products=" + products +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
