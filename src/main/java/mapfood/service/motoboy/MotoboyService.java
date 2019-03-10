package mapfood.service.motoboy;

import mapfood.dto.motoboy.MotoboyDto;

import java.util.List;

public interface MotoboyService {
    MotoboyDto save(MotoboyDto motoboyDto);

    MotoboyDto update(int id, MotoboyDto motoboyDto);

    MotoboyDto findById(int id);

    List<MotoboyDto> findAll();

    Boolean deleteById(int id);
}
