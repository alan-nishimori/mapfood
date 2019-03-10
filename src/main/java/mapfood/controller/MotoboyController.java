package mapfood.controller;

import mapfood.dto.motoboy.MotoboyDto;
import mapfood.service.motoboy.MotoboyService;
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

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("motoboy")
public class MotoboyController {

    @Autowired
    MotoboyService motoboyService;

    private final Logger logger = LoggerFactory.getLogger(MotoboyController.class);

    @PostMapping
    public ResponseEntity<MotoboyDto> create(@RequestBody final MotoboyDto motoboyDto) {
        logger.info("Starting motoboy creation - params: {}", motoboyDto);

        final MotoboyDto motoboy = motoboyService.save(motoboyDto);

        logger.info("Successfully created motoboy.");
        return new ResponseEntity<>(motoboy, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoboyDto> update(@PathVariable final int id, @RequestBody final MotoboyDto motoboyDto) {
        logger.info("Starting update on motoboy info with id: {} - params: {}", id, motoboyDto);

        final MotoboyDto motoboy = motoboyService.update(id, motoboyDto);

        if (Objects.isNull(motoboy)) {
            logger.warn("No motoboy found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Motoboy successfully updated");
        return new ResponseEntity<>(motoboy, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id) {
        logger.info("Starting delete of motoboy with id: {}", id);

        if (motoboyService.deleteById(id)) {
            logger.info("Successfully delete motoboy with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.warn("No motoboy found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Refatorar com paginação
    @GetMapping
    public ResponseEntity<List<MotoboyDto>> findAll() {
        logger.info("Getting all motoboys from the database.");

        final List<MotoboyDto> motoboys = motoboyService.findAll();

        logger.info("All motoboys successfully retrieved.");
        return new ResponseEntity<>(motoboys, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoboyDto> findById(@PathVariable final int id) {
        logger.info("Searching for motoboy with id: {}", id);

        final MotoboyDto motoboy = motoboyService.findById(id);

        if (Objects.isNull(motoboy)) {
            logger.warn("No motoboy found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Motoboy successfully retrieved.");
        return new ResponseEntity<>(motoboy, HttpStatus.OK);
    }
}
