package mapfood.dto.client;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ClientDto {

    @NotNull
    private Integer id;

    @NotNull
    private List<Double> location;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(final List<Double> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", location=" + location +
                '}';
    }
}
