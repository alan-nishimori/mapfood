package mapfood.dto.motoboy;

import mapfood.model.motoboy.MotoboyStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MotoboyDto {

    @NotNull
    private int id;

    @NotNull
    private List<Double> location;

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

    @Override
    public String toString() {
        return "MotoboyDto{" +
                "id=" + id +
                ", location=" + location +
                ", motoboyStatus=" + motoboyStatus +
                '}';
    }
}
