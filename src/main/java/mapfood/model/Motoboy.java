package mapfood.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motoboy")
public class Motoboy {

    @Id
    private Integer id;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "idx_geospatial")
    private GeoJsonPoint localization;

    private MotoboyStatus motoboyStatus = MotoboyStatus.AVAILABLE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GeoJsonPoint getLocalization() {
        return localization;
    }

    public void setLocalization(GeoJsonPoint localization) {
        this.localization = localization;
    }

    public MotoboyStatus getMotoboyStatus() {
        return motoboyStatus;
    }

    public void setMotoboyStatus(final MotoboyStatus motoboyStatus) {
        this.motoboyStatus = motoboyStatus;
    }
}
