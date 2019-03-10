package mapfood.repository.establishment;

import mapfood.model.establishment.Establishment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends MongoRepository<Establishment, String> {
}
