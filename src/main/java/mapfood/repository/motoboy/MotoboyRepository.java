package mapfood.repository.motoboy;

import mapfood.model.motoboy.Motoboy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotoboyRepository extends MongoRepository<Motoboy, Integer>, MotoboyRepositoryCustom {
}
