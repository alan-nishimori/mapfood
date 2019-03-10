package mapfood.controller;

import mapfood.dto.establishment.EstablishmentDto;
import mapfood.dto.establishment.product.ProductDto;
import mapfood.service.establishment.EstablishmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService establishmentService;

    private final Logger logger = LoggerFactory.getLogger(MotoboyController.class);

    @PostMapping
    public ResponseEntity<EstablishmentDto> save(@RequestBody @Valid EstablishmentDto establishmentDto) {
        logger.info("Starting establishment creation - params: {}", establishmentDto);

        final EstablishmentDto establishment = establishmentService.save(establishmentDto);

        logger.info("Successfully created establishment.");
        return new ResponseEntity<>(establishment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentDto> update(
            @PathVariable final String id, @RequestBody @Valid final EstablishmentDto establishmentDto) {
        logger.info("Starting update on establishment with id: {} - params: {}", id, establishmentDto);

        final EstablishmentDto establishment = establishmentService.update(id, establishmentDto);

        if (Objects.isNull(establishment)) {
            logger.warn("No establishment found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Establishment successfully updated");
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        logger.info("Starting delete of establishment with id: {}", id);

        if (establishmentService.deleteById(id)) {
            logger.info("Successfully deleted establishment with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.warn("No establishment found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO Refatorar com paginação
    @GetMapping
    public ResponseEntity<List<EstablishmentDto>> findAll() {
        logger.info("Getting all establishments from the database.");

        final List<EstablishmentDto> establishments = establishmentService.findAll();

        logger.info("All establishments successfully retrieved.");
        return new ResponseEntity<>(establishments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDto> findEstablishmentById(@PathVariable final String id) {
        logger.info("Searching for establishment with id: {}", id);

        final EstablishmentDto establishment = establishmentService.findById(id);

        if (Objects.isNull(establishment)) {
            logger.warn("No establishment found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Establishment Successfully retrieved.");
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<EstablishmentDto> addProduct(
            @PathVariable final String id, @RequestBody @Valid ProductDto productDto) {
        logger.info("Adding new product to establishment id: {} - params: {}", id, productDto);

        final EstablishmentDto establishment = establishmentService.addItem(id, productDto);

        if (Objects.isNull(establishment)) {
            logger.warn("No establishment found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Product successfully added to establishment: {}", id);
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

    @DeleteMapping("/{idEstablishment}/products/{idProduct}")
    public ResponseEntity<EstablishmentDto> removeProduct(
            @PathVariable final String idEstablishment, @PathVariable final String idProduct) {
        logger.info("Removing product: {} from establishment: {}", idProduct, idEstablishment);

        final EstablishmentDto establishment = establishmentService.removeItem(idEstablishment, idProduct);

        if (Objects.isNull(establishment)) {
            logger.warn("Product with id: {} or Establishment with id: {} not found", idProduct, idEstablishment);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Product successfully removed.");
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

    @PutMapping("/{idEstablishment}/products/{idProduct}")
    public ResponseEntity<EstablishmentDto> updateItem(
            @PathVariable final String idEstablishment,
            @PathVariable final String idProduct,
            @RequestBody @Valid ProductDto productDto
    ) {
        logger.info("Updating item: {} from establishment: {} - params: {}", idProduct, idEstablishment, productDto);

        EstablishmentDto establishment = establishmentService.updateItem(idEstablishment, idProduct, productDto);

        if (Objects.isNull(establishment)) {
            logger.warn("Product with id: {} or Establishment with id: {} not found", idProduct, idEstablishment);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Product successfully updated.");
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }
}
