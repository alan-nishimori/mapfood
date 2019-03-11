package mapfood.dto.order;

import mapfood.dto.establishment.product.ProductDto;
import mapfood.model.order.OrderStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    private Integer id;

    @NotEmpty
    private String establishmentId;

    @NotNull
    private Integer clientId;

    @NotNull
    private List<String> productsId;

    private List<ProductDto> productsDto;

    private OrderStatus orderStatus;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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

    public List<String> getProductsId() {
        return productsId;
    }

    public void setProductsId(final List<String> productsId) {
        this.productsId = productsId;
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
}
