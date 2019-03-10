package mapfood.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "establishment")
public class Establishment {

    @Id
    private String id;

    private String name;

    private String city;

    private String description;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "idx_geospatial")
    private GeoJsonPoint localization;

    private List<Product> products = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoJsonPoint getLocalization() {
        return localization;
    }

    public void setLocalization(GeoJsonPoint localization) {
        this.localization = localization;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", localization=" + localization +
                ", products=" + products +
                '}';
    }
}
