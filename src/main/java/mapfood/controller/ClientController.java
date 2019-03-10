package mapfood.controller;

import mapfood.dto.client.ClientDto;
import mapfood.service.client.ClientService;
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
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    private final Logger logger = LoggerFactory.getLogger(MotoboyController.class);

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid final ClientDto clientDto) {
        logger.info("Starting client creation - params: {}", clientDto);

        final ClientDto client = clientService.save(clientDto);

        logger.info("Successfully created client.");

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable final int id, @RequestBody @Valid final ClientDto clientDto) {
        logger.info("Starting update on motoboy info with id: {} - params: {}", id, clientDto);

        final ClientDto client = clientService.update(id, clientDto);

        if (Objects.isNull(client)) {
            logger.warn("No client found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Client successfully updated");
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id) {
        logger.info("Starting delete of client with id: {}", id);

        if (clientService.deleteById(id)) {
            logger.info("Successfully deleted client with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        logger.warn("No client found for id: {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO Refatorar com paginação
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        logger.info("Getting all clients from the database.");

        final List<ClientDto> clients = clientService.findAll();

        logger.info("All clients successfully retrieved.");
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable final int id) {
        logger.info("Searching for client with id: {}", id);

        final ClientDto client = clientService.findById(id);

        if (Objects.isNull(client)) {
            logger.warn("No client found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Client successfully retrieved.");
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
