package mapfood.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import mapfood.model.establishment.Establishment;
import mapfood.model.motoboy.MotoboyWithDistance;
import mapfood.repository.motoboy.impl.MotoboyRepositoryCustomImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MotoboyRepositoryTest {
    @TestConfiguration
    static class MotoboyRepositoryTestContextConfiguration {

        @Bean
        public MongoClient mongo(){
            MongoClientURI uri = new MongoClientURI("mongodb+srv://mapfood:U9mL4dYDeLdY6Wxe@aceleradev-mapfood-ztqqs.mongodb.net/test?retryWrites=true");
            return new MongoClient(uri);
        }

        @Bean
        public MongoTemplate mongoTemplate(MongoClient mongo){
            return new MongoTemplate(mongo, "mapfood");
        }

        @Bean
        public MotoboyRepositoryCustomImpl motoboyRepository(MongoTemplate mongoTemplate) {
            return new MotoboyRepositoryCustomImpl(mongoTemplate);
        }

    }

    @Autowired
    private MotoboyRepositoryCustomImpl repository;

    @Test
    public void shouldReturnClosestMotoboyForGivenEstablishmentId(){
        Establishment establishment = new Establishment();
        establishment.setLocation(new GeoJsonPoint(new Point(-46.691015, -23.620705)));

        MotoboyWithDistance target = new MotoboyWithDistance();
        target.setLocation(new GeoJsonPoint(new Point(-51.13475445, -30.03150529)));
        target.setDistance(0.13152995774874102);

        MotoboyWithDistance motoboy = repository.findClosestMotoboy(establishment.getLocation());

        Assertions.assertNotNull(motoboy);
        Assertions.assertEquals(target.getDistance(), motoboy.getDistance());
        Assertions.assertIterableEquals(motoboy.getLocation().getCoordinates(), target.getLocation().getCoordinates());
    }
}