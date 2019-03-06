package mapfood.controller;

import mapfood.dbstarter.ClientStarter;
import mapfood.dbstarter.MotoboyStarter;
import mapfood.model.Client;
import mapfood.model.Establishment;
import mapfood.model.Localization;
import mapfood.model.Motoboy;
import mapfood.model.Product;
import mapfood.repository.ClientRepository;
import mapfood.repository.EstablishmentRepository;
import mapfood.repository.MotoboyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    MotoboyRepository motoboyRepository;

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public HttpStatus test() {
        System.out.println("Total clients: " + clientRepository.count());

        System.out.println("Total motoboys: " + motoboyRepository.count());

        return HttpStatus.OK;
    }

    @GetMapping("/populate-client")
    public HttpStatus populateClient() {
        List<Client> clients = ClientStarter.clientStart();
        clientRepository.saveAll(clients);

        return HttpStatus.OK;
    }

    @GetMapping("/populate-motoboy")
    public HttpStatus populateMotoboy() {
        Instant now = Instant.now();
        logger.info("Starting method, time{}", now);
        List<Motoboy> motoboys = MotoboyStarter.motoboyStart();
        motoboyRepository.saveAll(motoboys);
        now = Instant.now();
        logger.info("Finished method, time{}", now);
        return HttpStatus.OK;
    }
}
