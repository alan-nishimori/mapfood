package mapfood.repository.motoboy;

import mapfood.model.motoboy.MotoboyWithDistance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface MotoboyRepositoryCustom {
    List<MotoboyWithDistance> findClosestMotoboy(GeoJsonPoint point);
}
