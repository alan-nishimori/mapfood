package mapfood.dto.motoboy;

import mapfood.model.motoboy.MotoboyStatus;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;

public class MotoboyDto {

    @NotNull
    private int id;

    @NotNull
    private GeoJsonPoint location;

    @NotNull
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
