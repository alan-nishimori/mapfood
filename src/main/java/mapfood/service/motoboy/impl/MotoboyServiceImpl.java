package mapfood.service.motoboy.impl;

import mapfood.converter.motoboy.MotoboyEntityToDto;
import mapfood.dto.motoboy.MotoboyDto;
import mapfood.model.motoboy.Motoboy;
import mapfood.repository.motoboy.MotoboyRepository;
import mapfood.service.motoboy.MotoboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    @Autowired
    MotoboyRepository motoboyRepository;

    @Override
    public MotoboyDto save(final MotoboyDto motoboyDto) {

        final Motoboy motoboy = new Motoboy();
        motoboy.setId(motoboyDto.getId());
        motoboy.setLocation(new GeoJsonPoint(motoboyDto.getLocation().get(0), motoboyDto.getLocation().get(1)));

        return new MotoboyEntityToDto(motoboyRepository.save(motoboy)).build();
    }

    @Override
    public MotoboyDto update(final int id, final MotoboyDto motoboyDto) {
        final Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        if (motoboy.isPresent()) {
            motoboy.get().setLocation(new GeoJsonPoint(motoboyDto.getLocation().get(0), motoboyDto.getLocation().get(1)));
            motoboy.get().setMotoboyStatus(motoboyDto.getMotoboyStatus());
            return new MotoboyEntityToDto(motoboyRepository.save(motoboy.get())).build();
        }

        return null;
    }

    @Override
    public MotoboyDto findById(final int id) {
        final Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        return motoboy.map(motoboy1 -> new MotoboyEntityToDto(motoboy1).build()).orElse(null);
    }

    @Override
    public List<MotoboyDto> findAll() {
        final List<Motoboy> motoboyEntities = motoboyRepository.findAll();
        final List<MotoboyDto> motoboyDtos = new ArrayList<>();

        motoboyEntities.forEach(motoboy -> motoboyDtos.add(new MotoboyEntityToDto(motoboy).build()));

        return motoboyDtos;
    }

    @Override
    public Boolean deleteById(final int id) {
        final Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        if (motoboy.isPresent()) {
            motoboyRepository.deleteById(id);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
