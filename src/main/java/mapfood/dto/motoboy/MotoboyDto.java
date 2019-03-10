package mapfood.dto.motoboy;

import mapfood.model.MotoboyStatus;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class MotoboyDto {

    private int id;

    private GeoJsonPoint location;

    private MotoboyStatus motoboyStatus;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(final GeoJsonPoint location) {
        this.location = location;
    }

    public MotoboyStatus getMotoboyStatus() {
        return motoboyStatus;
    }

    public void setMotoboyStatus(final MotoboyStatus motoboyStatus) {
        this.motoboyStatus = motoboyStatus;
    }
}
