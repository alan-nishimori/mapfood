package mapfood.repository;

import mapfood.model.Motoboy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotoboyRepository extends MongoRepository<Motoboy, Integer>, MotoboyRepositoryCustom {
}
