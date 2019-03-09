package mapfood.service.gmaps;

import mapfood.dto.google.maps.api.DirectionsResult;
import mapfood.model.Localization;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;

public interface DirectionsService {

    DirectionsResult getDistance(Localization origin, Localization destination, List<Localization> waypoints) throws InvalidKeyException, InterruptedException, IOException;

}

