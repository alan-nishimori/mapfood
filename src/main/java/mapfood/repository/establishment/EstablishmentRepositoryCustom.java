package mapfood.repository.establishment;

import mapfood.model.establishment.EstablishmentWithDistance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface EstablishmentRepositoryCustom {
    List<EstablishmentWithDistance> findEstablishmentsNextToClient(GeoJsonPoint point);
}
