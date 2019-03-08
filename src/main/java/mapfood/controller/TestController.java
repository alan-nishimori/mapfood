package mapfood.controller;

import mapfood.dto.google.maps.api.DirectionsResult;
import mapfood.model.Localization;
import mapfood.repository.ClientRepository;
import mapfood.repository.EstablishmentRepository;
import mapfood.service.gmaps.DirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    DirectionsService directionsService;

    @GetMapping
    public HttpStatus test() throws InterruptedException, InvalidKeyException, IOException {

        Localization l1 = new Localization();
        l1.setLatitude(-22.6573743);
        l1.setLongitude(-50.4270745);

        Localization l2 = new Localization();
        l2.setLatitude(-21.7794567);
        l2.setLongitude(-48.1928462);

        Localization l3 = new Localization();
        l3.setLatitude(-22.296561);
        l3.setLongitude(-49.069859);

        Localization l4 = new Localization();
        l4.setLatitude(-22.306535);
        l4.setLongitude(-48.575107);

        List<Localization> localizations = new ArrayList<>();
        localizations.add(l4);
        localizations.add(l3);

        final DirectionsResult distance = directionsService.getDistance(l1, l2, localizations);

        return HttpStatus.OK;
    }

}
