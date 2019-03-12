package mapfood.dto.establishment;

import mapfood.dto.establishment.product.ProductDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String city;

    @NotEmpty
    private String description;

    @NotNull
    private List<Double> location;

    private List<ProductDto> products = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(final List<Double> location) {
        this.location = location;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void addProduct(final ProductDto productDto) {
        this.products.add(productDto);
    }

    @Override
    public String toString() {
        return "EstablishmentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", products=" + products +
                '}';
    }
}
