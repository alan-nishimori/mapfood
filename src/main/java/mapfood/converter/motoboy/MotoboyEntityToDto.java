package mapfood.converter.motoboy;

import mapfood.dto.motoboy.MotoboyDto;
import mapfood.model.motoboy.Motoboy;

public class MotoboyEntityToDto {

    private final Motoboy motoboy;

    public MotoboyEntityToDto(final Motoboy motoboy) {
        this.motoboy = motoboy;
    }

    public MotoboyDto build() {
        MotoboyDto motoboyDto = new MotoboyDto();
        motoboyDto.setId(motoboy.getId());
        motoboyDto.setLocation(motoboy.getLocation());
        motoboyDto.setMotoboyStatus(motoboy.getMotoboyStatus());
        return motoboyDto;
    }
}
