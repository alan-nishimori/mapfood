package mapfood.model.motoboy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motoboy")
public class Motoboy {

    @Id
    private Integer id;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "idx_geospatial")
    private GeoJsonPoint location;

    private MotoboyStatus motoboyStatus = MotoboyStatus.AVAILABLE;

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

    public MotoboyStatus getMotoboyStatus() {
        return motoboyStatus;
    }

    public void setMotoboyStatus(final MotoboyStatus motoboyStatus) {
        this.motoboyStatus = motoboyStatus;
    }
}
