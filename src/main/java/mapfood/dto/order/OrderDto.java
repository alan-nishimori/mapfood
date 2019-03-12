package mapfood.dto.order;

import mapfood.dto.establishment.product.ProductDto;
import mapfood.model.order.OrderStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    private String id;

    @NotEmpty
    private String establishmentId;

    @NotNull
    private Integer clientId;

    @NotNull
    private List<ProductDto> productsDto;

    private OrderStatus orderStatus;

    private Double value;

    private Instant createdAt;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(final String establishmentId) {
        this.establishmentId = establishmentId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }

    public List<ProductDto> getProductsDto() {
        return productsDto;
    }

    public void addProduct(ProductDto productDto) {
        if (Objects.isNull(this.productsDto)) {
            this.productsDto = new ArrayList<>();
        }
        productsDto.add(productDto);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id='" + id + '\'' +
                ", establishmentId='" + establishmentId + '\'' +
                ", clientId=" + clientId +
                ", productsDto=" + productsDto +
                ", orderStatus=" + orderStatus +
                ", value=" + value +
                ", createdAt=" + createdAt +
                '}';
    }
}
