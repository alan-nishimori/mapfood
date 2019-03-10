package mapfood.service;

import mapfood.model.Establishment;
import mapfood.model.MotoboyWithDistance;
import mapfood.repository.ClientRepository;
import mapfood.repository.EstablishmentRepository;
import mapfood.repository.MotoboyRepository;
import mapfood.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapfoodServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MotoboyRepository motoboyRepository;

    @InjectMocks
    private MapfoodServiceImpl service;

    @Test
    public void shouldReturnClosestMotoboyForGivenEstablishmentId(){

        Establishment establishment = new Establishment();
        establishment.setLocalization(new GeoJsonPoint(new Point(-46.691015, -23.620705)));

        when(establishmentRepository.findById(any(String.class))).thenReturn(Optional.of(establishment));

        MotoboyWithDistance target = new MotoboyWithDistance();
        target.setLocalization(new GeoJsonPoint(new Point(-51.13475445, -30.03150529)));
        target.setDistance(0.13152995774874102);
        when(motoboyRepository.findClosestMotoboy(any(GeoJsonPoint.class))).thenReturn(target);

        MotoboyWithDistance motoboy = service.getClosestMotoboy("1");

        Assertions.assertNotNull(motoboy);
        Assertions.assertEquals(target.getDistance(), motoboy.getDistance());
        Assertions.assertIterableEquals(motoboy.getLocalization().getCoordinates(), target.getLocalization().getCoordinates());
    }
}