package mapfood.controller;

import mapfood.controller.client.ClientController;
import mapfood.dto.google.maps.api.DirectionsResult;
import mapfood.model.client.Client;
import mapfood.model.establishment.EstablishmentWithDistance;
import mapfood.repository.client.ClientRepository;
import mapfood.repository.establishment.EstablishmentRepository;
import mapfood.service.gmaps.DirectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    DirectionsService directionsService;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public HttpStatus test() throws InterruptedException, InvalidKeyException, IOException {

        GeoJsonPoint l1 = new GeoJsonPoint(-50.4270745, -22.6573743);

        GeoJsonPoint l2 = new GeoJsonPoint(-48.1928462, -21.7794567);

        GeoJsonPoint l3 = new GeoJsonPoint(-49.069859, -22.296561);

        GeoJsonPoint l4 = new GeoJsonPoint(-48.575107, -22.306535);

        List<GeoJsonPoint> localizations = new ArrayList<>();
        localizations.add(l4);
        localizations.add(l3);

        final DirectionsResult distance = directionsService.getDistance(l1, l2, localizations);

        return HttpStatus.OK;
    }

    @GetMapping(value = "/establishments/nextTo/{id}")
    public ResponseEntity<List<EstablishmentWithDistance>> getEstablishmentsNextToClient(@PathVariable("id") Integer clientId) {
        logger.info("Starting test getting - params: {}", clientId);

        Optional<Client> client = clientRepository.findById(clientId);
        if (!client.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<EstablishmentWithDistance> establishments = establishmentRepository.findEstablishmentsNextToClient(client.get().getLocation());

        logger.info("{} establishments finded.", establishments.size());

        return new ResponseEntity<>(establishments, HttpStatus.OK);
    }

}
