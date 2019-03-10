package mapfood.service.gmaps;

import mapfood.dto.google.maps.api.DirectionsResult;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;

public interface DirectionsService {

    DirectionsResult getDistance(GeoJsonPoint origin, GeoJsonPoint destination, List<GeoJsonPoint> waypoints) throws InvalidKeyException, InterruptedException, IOException;

}

