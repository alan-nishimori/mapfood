package mapfood.converter.establishment;

import mapfood.converter.product.ProductEntityToDto;
import mapfood.dto.establishment.EstablishmentDto;
import mapfood.model.establishment.EstablishmentWithDistance;

public class EstablishmentWithDistanceToDto {

    final EstablishmentWithDistance establishmentWithDistance;

    public EstablishmentWithDistanceToDto(final EstablishmentWithDistance establishmentWithDistance) {
        this.establishmentWithDistance = establishmentWithDistance;
    }

    public EstablishmentDto build() {
        EstablishmentDto establishmentDto = new EstablishmentDto();
        establishmentDto.setId(establishmentWithDistance.getId());
        establishmentDto.setName(establishmentWithDistance.getName());
        establishmentDto.setCity(establishmentWithDistance.getCity());
        establishmentDto.setDescription(establishmentWithDistance.getDescription());

        establishmentDto.setLocation(establishmentWithDistance.getLocation().getCoordinates());
        establishmentWithDistance.getProducts()
                .forEach(product -> establishmentDto.addProduct(new ProductEntityToDto(product).build()));

        return establishmentDto;
    }
}
