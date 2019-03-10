package mapfood.repository.motoboy;

import mapfood.model.motoboy.MotoboyWithDistance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public interface MotoboyRepositoryCustom {
    MotoboyWithDistance findClosestMotoboy(GeoJsonPoint point);
}
