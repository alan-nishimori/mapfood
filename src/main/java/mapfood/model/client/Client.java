package mapfood.model.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class Client {

    @Id
    private Integer id;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "idx_geospatial")
    private GeoJsonPoint location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
