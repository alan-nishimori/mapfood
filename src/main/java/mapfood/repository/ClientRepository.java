package mapfood.repository;

import mapfood.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends MongoRepository<ClientEntity, Integer> {
}
