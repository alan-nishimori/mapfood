package mapfood.service.gmaps.impl;

import mapfood.dto.google.maps.api.DirectionsResult;
import mapfood.service.gmaps.DirectionsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Objects;

@Service
public class DirectionsServiceImpl implements DirectionsService {

    @Value("${google.maps.url}")
    private String baseUrl;

    @Value("${google.maps.key}")
    private String apiKey;

    @Override
    public DirectionsResult getDistance(GeoJsonPoint origin, GeoJsonPoint destination, List<GeoJsonPoint> waypoints)
            throws IOException, InvalidKeyException {

        final String originString = origin.getY() + "," + origin.getX();
        baseUrl = baseUrl.replace("[lat1],[lng1]", originString);

        final String destinationString = destination.getY() + "," + destination.getX();
        baseUrl = baseUrl.replace("[lat2],[lng2]", destinationString);

        baseUrl = baseUrl.concat("&alternatives=false");

        if (!Objects.isNull(waypoints)) {
            baseUrl = baseUrl.concat("&waypoints=optimize:true");
            for (GeoJsonPoint waypoint : waypoints) {
                baseUrl = baseUrl.concat("|" + waypoint.getY() + "," + waypoint.getX());
            }
        }

        baseUrl = baseUrl.concat("&key="+apiKey);

        String request = "";
        String urlRequest = new URL(baseUrl).toString();

        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Requested-With", "XMLHttpRequest");
        headers.add("Cache-Control", "no-cache");

        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<DirectionsResult> result;

        result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, httpEntity, DirectionsResult.class);

        if (!("OK").equals(Objects.requireNonNull(result.getBody()).status)) {
            throw new InvalidKeyException();
        }

        return result.getBody();

    }
}
