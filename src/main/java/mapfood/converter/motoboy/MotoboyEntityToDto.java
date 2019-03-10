package mapfood.converter.motoboy;

import mapfood.dto.motoboy.MotoboyDto;
import mapfood.model.motoboy.Motoboy;

import java.util.ArrayList;
import java.util.List;

public class MotoboyEntityToDto {

    private final Motoboy motoboy;

    public MotoboyEntityToDto(final Motoboy motoboy) {
        this.motoboy = motoboy;
    }

    public MotoboyDto build() {
        List<Double> location = new ArrayList<>();
        location.add(motoboy.getLocation().getX());
        location.add(motoboy.getLocation().getY());

        MotoboyDto motoboyDto = new MotoboyDto();
        motoboyDto.setId(motoboy.getId());
        motoboyDto.setLocation(location);
        motoboyDto.setMotoboyStatus(motoboy.getMotoboyStatus());

        return motoboyDto;
    }
}
