package mapfood.controller.delivery;

import mapfood.dto.delivery.DeliveryDto;
import mapfood.model.delivery.DeliveryStatus;
import mapfood.service.delivery.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    private final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @PostMapping("/{id}")
    public ResponseEntity<DeliveryDto> updateStatus(
            @PathVariable final String id, @RequestBody final DeliveryStatus deliveryStatus) {
        logger.info("Updating delivery: {} with status: {}", id, deliveryStatus);

        try {
            final DeliveryDto delivery = deliveryService.updateStatus(id, deliveryStatus);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDto> findById(@PathVariable final String id) {
        logger.info("Searching for delivery with id: {}", id);

        final DeliveryDto delivery = deliveryService.findById(id);

        if (Objects.isNull(delivery)) {
            logger.warn("No delivery found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/motoboy/{id}")
    public ResponseEntity<List<DeliveryDto>> findByMotoboy(@PathVariable final Integer id) {
        logger.info("Searching for delivery by motoboy id: {}", id);

        final List<DeliveryDto> deliveries = deliveryService.findByMotoboy(id);

        logger.info("Successfully retrieved deliveries for motoboy with id: {}", id);
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/motoboy/{id}/open")
    public ResponseEntity<DeliveryDto> findByMotoboyOpen(@PathVariable final Integer id) {
        logger.info("Searching for delivery with status open for motoboy with id: {}", id);

        final DeliveryDto delivery = deliveryService.findDeliveryNotFinishedByMotoboy(id);

        if (Objects.isNull(delivery)) {
            logger.warn("No delivery found with status open for motoboy with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Successfully retrieved delivery with status open for motoboy with id: {]", id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<DeliveryDto> findByOrder(@PathVariable final String id) {
        logger.info("Searching for delivery for order id: {}", id);

        final DeliveryDto delivery = deliveryService.findByOrder(id);

        if (Objects.isNull(delivery)) {
            logger.warn("No delivery found for order id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Successfully retrieved delivery for order id: {]", id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/order/{id}/route")
    public ResponseEntity<Map<String, List<String>>> getRoute(@PathVariable final String id) {
        logger.info("Searching for delivery with id: {} to get route", id);

        try {
            final Map<String, List<String>> route = deliveryService.getRoute(id);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.warn("No delivery found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
