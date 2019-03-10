package mapfood.service;

import mapfood.model.Client;
import mapfood.model.Establishment;
import mapfood.model.MotoboyWithDistance;
import mapfood.model.Order;
import mapfood.repository.ClientRepository;
import mapfood.repository.EstablishmentRepository;
import mapfood.repository.MotoboyRepository;
import mapfood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class MapfoodServiceImpl implements MapfoodService {

    @Autowired
    private OrderRepository OrderRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Override
    public Order createOrder(Order order) {
        Optional<Establishment> establishment = establishmentRepository.findById(order.getEstablishmentId());
        if (!establishment.isPresent()) {
            throw new RuntimeException("Estabelecimento nao localizado");
        }

        Optional<Client> client = clientRepository.findById(order.getClientId());
        if (!client.isPresent()){
            throw new RuntimeException("Cliente nao localizado");
        }

        if (order.getProducts().isEmpty()){
            throw new RuntimeException("Pedido seu produto!");
        }

        order.setCreatedAt(new Date());
        return OrderRepository.save(order);
    }

    @Override
    public Order getOrder(Integer id) {
        return OrderRepository.findById(id)
                .orElse(null);
    }

    @Override
    public MotoboyWithDistance getClosestMotoboy(String EstablishmentId) {
        Optional<Establishment> establishment = establishmentRepository.findById(EstablishmentId);
        if (!establishment.isPresent()) {
            throw new RuntimeException("Estabelecimento nao localizado");
        }

        return motoboyRepository.findClosestMotoboy(establishment.get().getLocalization());
    }
}
