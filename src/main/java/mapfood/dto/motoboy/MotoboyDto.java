package mapfood.dto.motoboy;

import mapfood.model.motoboy.MotoboyStatus;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MotoboyDto {

    @NotNull
    private int id;

    @NotNull
    private List<Double> location;

    @NotNull
    private MotoboyStatus motoboyStatus;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(final List<Double> location) {
        this.location = location;
    }

    public MotoboyStatus getMotoboyStatus() {
        return motoboyStatus;
    }

    public void setMotoboyStatus(final MotoboyStatus motoboyStatus) {
        this.motoboyStatus = motoboyStatus;
    }
}
