package mapfood.converter.establishment;

import mapfood.converter.product.ProductEntityToDto;
import mapfood.dto.establishment.EstablishmentDto;
import mapfood.dto.establishment.product.ProductDto;
import mapfood.model.establishment.Establishment;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentEntityToDto {

    private final Establishment establishment;

    public EstablishmentEntityToDto(final Establishment establishment) {
        this.establishment = establishment;
    }

    public EstablishmentDto build() {
        EstablishmentDto establishmentDto = new EstablishmentDto();
        List<ProductDto> productsDto = new ArrayList<>();
        List<Double> location = new ArrayList<>();

        establishment.getProducts().forEach(product -> productsDto.add(new ProductEntityToDto(product).build()));

        location.add(establishment.getLocation().getX());
        location.add(establishment.getLocation().getY());

        establishmentDto.setId(establishment.getId());
        establishmentDto.setName(establishment.getName());
        establishmentDto.setCity(establishment.getCity());
        establishmentDto.setDescription(establishment.getDescription());
        establishmentDto.setLocation(location);
        establishmentDto.setProducts(productsDto);

        return establishmentDto;
    }
}
