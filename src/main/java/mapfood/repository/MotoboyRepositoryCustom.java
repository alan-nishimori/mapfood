package mapfood.repository;

import mapfood.model.MotoboyWithDistance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public interface MotoboyRepositoryCustom {
    MotoboyWithDistance findClosestMotoboy(GeoJsonPoint point);
}
