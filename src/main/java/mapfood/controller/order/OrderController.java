package mapfood.controller.order;

import mapfood.dto.order.OrderDto;
import mapfood.model.order.OrderStatus;
import mapfood.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody @Valid final OrderDto orderDto) {
        logger.info("Starting order creation - params: {}", orderDto);

        final OrderDto order;

        try {
            order = orderService.save(orderDto);
        } catch (final RuntimeException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Order successfully created.");
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable final String id, @RequestBody final OrderStatus orderStatus) {
        logger.info("Starting update on order status");

        final OrderDto order = orderService.update(id, orderStatus);

        if (Objects.isNull(order)) {
            logger.warn("Order with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Status of order with id: {} sucessfully updated", id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable final String id) {
        logger.info("Searching for order with id: {}", id);

        final OrderDto order = orderService.findById(id);

        if (Objects.isNull(order)) {
            logger.warn("Order with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Successfully retrieved order with id: {}", id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // TODO paginar todos

    @GetMapping("/client/{id}")
    public ResponseEntity<List<OrderDto>> findAllByClientId(@PathVariable final Integer id) {
        logger.info("Searching all orders from client with id: {}", id);

        final List<OrderDto> orders = orderService.findAllByClientId(id);

        logger.info("Successfully retrieved all orders from client with id: {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<List<OrderDto>> findAllByEstablishmentId(@PathVariable final String id) {
        logger.info("Searching all orders from establishment with id: {}", id);

        final List<OrderDto> orders = orderService.findAllByEstablishmentId(id);

        logger.info("Successfully retrieved all orders from establishment with id: {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("establishment/{id}/range")
    public ResponseEntity<List<OrderDto>> findAllByEstablishmentIdAndDateBetween(
            @PathVariable final String id,
            @RequestParam (required = true) final Instant from,
            @RequestParam (required = true) final Instant to
    ) {
        logger.info("Searching all orders from establishment id: {} - from: {} to: {}", id, from, to);

        final List<OrderDto> orders = orderService.findAllByEstablishmentIdAndDateBetween(id, from, to);

        logger.info("Successfully retrieved all orders from establishment id: {} - from: {} to: {}", id, from, to);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("establishment/{id}/date-status")
    public ResponseEntity<List<OrderDto>> findAllByEstablishmentIdAndDateWithStatus(
            @PathVariable final String id,
            @RequestParam (required = true) final Instant date,
            @RequestParam (required = true) final OrderStatus orderStatus
    ) {
        logger.info("Searching all orders from establishment id: {} at: {} with status: {}", id, date, orderStatus);

        final List<OrderDto> orders = orderService.findAllByEstablishmentIdAndDateWithStatus(id, date, orderStatus);

        logger.info("Successfully retrieved all orders from establishment id: {} at: {} with status: {}", id, date, orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
