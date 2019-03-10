package mapfood.controller;

import mapfood.dto.google.maps.api.DirectionsResult;
import mapfood.service.gmaps.DirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
    DirectionsService directionsService;

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

}
