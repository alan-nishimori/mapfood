package mapfood.dto.establishment.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto {

    @NotEmpty
    private String id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String classification;

    @NotNull
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(final String classification) {
        this.classification = classification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", classification='" + classification + '\'' +
                ", price=" + price +
                '}';
    }
}
