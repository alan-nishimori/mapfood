package mapfood.service.establishment.impl;

import mapfood.converter.establishment.EstablishmentEntityToDto;
import mapfood.converter.establishment.EstablishmentWithDistanceToDto;
import mapfood.dto.establishment.EstablishmentDto;
import mapfood.dto.establishment.product.ProductDto;
import mapfood.model.establishment.Establishment;
import mapfood.model.establishment.EstablishmentWithDistance;
import mapfood.model.establishment.product.Product;
import mapfood.repository.establishment.EstablishmentRepository;
import mapfood.service.establishment.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    EstablishmentRepository repository;

    @Override
    public EstablishmentDto save(final EstablishmentDto establishmentDto) {
        final Establishment establishment = new Establishment();
        establishment.setId(establishmentDto.getId());
        establishment.setName(establishmentDto.getName());
        establishment.setCity(establishmentDto.getCity());
        establishment.setDescription(establishmentDto.getDescription());
        establishment.setLocation(
                new GeoJsonPoint(establishmentDto.getLocation().get(0), establishmentDto.getLocation().get(1))
        );

        if (Objects.nonNull(establishmentDto.getProducts())) {
            populateProduct(establishmentDto, establishment);
        }

        return new EstablishmentEntityToDto(repository.save(establishment)).build();
    }

    @Override
    public EstablishmentDto update(final String id, final EstablishmentDto establishmentDto) {
        Optional<Establishment> establishment = repository.findById(id);

        if (establishment.isPresent()) {
            establishment.get().setName(establishmentDto.getName());
            establishment.get().setCity(establishmentDto.getCity());
            establishment.get().setDescription(establishmentDto.getDescription());
            establishment.get().setLocation(
                    new GeoJsonPoint(establishmentDto.getLocation().get(0), establishmentDto.getLocation().get(1))
            );

            if (Objects.nonNull(establishmentDto.getProducts())) {
                populateProduct(establishmentDto, establishment.get());
            }

            return new EstablishmentEntityToDto(repository.save(establishment.get())).build();
        }

        return null;
    }

    @Override
    public EstablishmentDto findById(final String id) {
        final Optional<Establishment> establishment = repository.findById(id);

        return establishment.map(establishment1 -> new EstablishmentEntityToDto(establishment1).build()).orElse(null);
    }

    // TODO adicionar paginação
    @Override
    public List<EstablishmentDto> findAll() {
        final List<Establishment> establishments = repository.findAll();
        final List<EstablishmentDto> establishmentsDto = new ArrayList<>();

        establishments.forEach(establishment -> establishmentsDto.add(new EstablishmentEntityToDto(establishment).build()));

        return establishmentsDto;
    }

    @Override
    public Boolean deleteById(final String id) {
        final Optional<Establishment> establishment = repository.findById(id);

        if (establishment.isPresent()) {
            repository.deleteById(id);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public EstablishmentDto addProduct(final String idEstablishment, final ProductDto productDto) {
        final Optional<Establishment> establishment = repository.findById(idEstablishment);

        if (establishment.isPresent()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setDescription(productDto.getDescription());
            product.setClassification(productDto.getClassification());
            product.setPrice(productDto.getPrice());

            establishment.get().addProduct(product);
            return new EstablishmentEntityToDto(repository.save(establishment.get())).build();
        }

        return null;
    }

    @Override
    public EstablishmentDto removeProduct(final String idEstablishment, final String idProduct) throws RuntimeException {
        final Optional<Establishment> establishment = repository.findById(idEstablishment);

        if (establishment.isPresent()) {
            for (int i = 0; i < establishment.get().getProducts().size(); i++) {
                Product product = establishment.get().getProducts().get(i);
                if (product.getId().equals(idProduct)) {
                    establishment.get().removeProduct(product);
                    return new EstablishmentEntityToDto(repository.save(establishment.get())).build();
                }
            }
            throw new RuntimeException("Product with id: " + idProduct + " not found");
        }

        throw new RuntimeException("Establishment with id: " + idEstablishment + " not found");
    }

    @Override
    public EstablishmentDto updateProduct(
            final String idEstablishment, final String idProduct, final ProductDto productDto) throws RuntimeException {
        final Optional<Establishment> establishment = repository.findById(idEstablishment);

        if (establishment.isPresent()) {
            for (int i = 0; i < establishment.get().getProducts().size(); i++) {
                Product product = establishment.get().getProducts().get(i);
                if (product.getId().equals(idProduct)) {
                    product.setDescription(productDto.getDescription());
                    product.setClassification(productDto.getClassification());
                    product.setPrice(productDto.getPrice());
                    return new EstablishmentEntityToDto(repository.save(establishment.get())).build();
                }
            }
            throw new RuntimeException("Product with id: " + idProduct + " not found");
        }

        throw new RuntimeException("Establishment with id: " + idEstablishment + " not found");
    }

    @Override
    public List<EstablishmentDto> findNearbyByLocation(final GeoJsonPoint point) {
        final List<EstablishmentWithDistance> establishments = repository.findEstablishmentsNextToClient(point);
        final List<EstablishmentDto> establishmentsDto = new ArrayList<>();

        establishments.forEach(establishment ->
                establishmentsDto.add(new EstablishmentWithDistanceToDto(establishment).build()));

        return establishmentsDto;
    }

    private void populateProduct(final EstablishmentDto establishmentDto, final Establishment establishment) {
        establishmentDto.getProducts().forEach(productDto -> {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setClassification(productDto.getClassification());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());

            establishment.addProduct(product);
        });
    }
}
