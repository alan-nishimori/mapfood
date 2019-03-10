package mapfood.service.establishment;

import mapfood.dto.establishment.EstablishmentDto;
import mapfood.dto.establishment.product.ProductDto;

import java.util.List;

public interface EstablishmentService {

    EstablishmentDto save(EstablishmentDto establishmentDto);

    EstablishmentDto update(String id, EstablishmentDto establishmentDto);

    EstablishmentDto findById(String id);

    List<EstablishmentDto> findAll();

    Boolean deleteById(String id);

    EstablishmentDto addItem(String idEstablishment, ProductDto productDto);

    EstablishmentDto removeItem(String idEstablishment, String idItem);

    EstablishmentDto updateItem(String idEstablishment, String idItem, ProductDto productDto);
}
