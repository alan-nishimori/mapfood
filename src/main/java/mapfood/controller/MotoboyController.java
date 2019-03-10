package mapfood.controller;

import mapfood.model.Localization;
import mapfood.model.Motoboy;
import mapfood.repository.MotoboyRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("motoboy")
public class MotoboyController {

    @Autowired
    MotoboyRepository motoboyRepository;

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping
    public ResponseEntity<Motoboy> create(@RequestBody final Motoboy motoboyDto) {
        logger.info("Starting motoboy creation - params: {}", motoboyDto);

        Localization localization = new Localization();
        localization.setLongitude(motoboyDto.getLocalization().getLongitude());
        localization.setLatitude(motoboyDto.getLocalization().getLatitude());

        Motoboy motoboy = new Motoboy();
        motoboy.setId(motoboyDto.getId());
        motoboy.setLocalization(localization);

        final Motoboy motoboyEntity = motoboyRepository.save(motoboy);
        logger.info("Successfully created motoboy.");
        return new ResponseEntity<>(motoboyEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motoboy> update(@PathVariable final int id, @RequestBody final Motoboy motoboyDto) {
        logger.info("Starting update on motoboy info with id: {} - params: {}", id, motoboyDto);

        Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        if (motoboy.isPresent()) {
            Localization localization = new Localization();

            localization.setLongitude(motoboy.get().getLocalization().getLongitude());
            localization.setLatitude(motoboy.get().getLocalization().getLatitude());
            motoboy.get().setLocalization(localization);

            final Motoboy motoboyEntity = motoboyRepository.save(motoboy.get());
            logger.info("Motoboy successfully updated");
            return new ResponseEntity<>(motoboyEntity, HttpStatus.OK);
        }

        logger.warn("No motoboy found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id) {
        logger.info("Starting delete of motoboy with id: {}", id);

        final Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        if (motoboy.isPresent()) {
            motoboyRepository.deleteById(id);
            logger.info("Successfully delete motoboy with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.warn("No motoboy found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Refatorar com paginação
    @GetMapping
    public ResponseEntity<List<Motoboy>> findAll() {
        logger.info("Getting all motoboys from the database.");

        final List<Motoboy> motoboys = motoboyRepository.findAll();

        logger.info("All motoboys retrieved.");
        return new ResponseEntity<>(motoboys, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motoboy> findById(@PathVariable final int id) {
        logger.info("Searching for motoboy with id: {}", id);

        final Optional<Motoboy> motoboy = motoboyRepository.findById(id);

        if (motoboy.isPresent()) {
            logger.info("Motoboy successfully found.");
            return new ResponseEntity<>(motoboy.get(), HttpStatus.OK);
        }

        logger.warn("No motoboy found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
