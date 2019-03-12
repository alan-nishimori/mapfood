package mapfood.service.establishment;

import mapfood.dto.establishment.EstablishmentDto;
import mapfood.dto.establishment.product.ProductDto;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface EstablishmentService {

    EstablishmentDto save(EstablishmentDto establishmentDto);

    EstablishmentDto update(String id, EstablishmentDto establishmentDto);

    EstablishmentDto findById(String id);

    List<EstablishmentDto> findAll();

    Boolean deleteById(String id);

    EstablishmentDto addProduct(String idEstablishment, ProductDto productDto);

    EstablishmentDto removeProduct(String idEstablishment, String idProduct) throws RuntimeException;

    EstablishmentDto updateProduct(String idEstablishment, String idProduct, ProductDto productDto) throws RuntimeException;

    List<EstablishmentDto> findNearbyByLocation(GeoJsonPoint point);
}
