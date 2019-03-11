package mapfood.service.order.impl;

import mapfood.converter.order.OrderEntityToDto;
import mapfood.dto.order.OrderDto;
import mapfood.model.client.Client;
import mapfood.model.establishment.Establishment;
import mapfood.model.establishment.product.Product;
import mapfood.model.order.Order;
import mapfood.model.order.OrderStatus;
import mapfood.repository.client.ClientRepository;
import mapfood.repository.establishment.EstablishmentRepository;
import mapfood.repository.order.OrderRepository;
import mapfood.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Override
    public OrderDto save(final OrderDto orderDto) throws RuntimeException{
        final Optional<Client> client = clientRepository.findById(orderDto.getClientId());
        if (!client.isPresent()) {
            throw new RuntimeException("No client found with id: " + orderDto.getClientId());
        }

        final Optional<Establishment> establishment = establishmentRepository.findById(orderDto.getEstablishmentId());
        if (!establishment.isPresent()) {
            throw new RuntimeException("No establishment found with id: " + orderDto.getEstablishmentId());
        }

        final Order order = new Order();
        order.setClientId(orderDto.getClientId());
        order.setEstablishmentId(orderDto.getEstablishmentId());

        orderDto.getProductsDto().forEach(productDto -> {
            final Product product = establishment.get().getProductById(productDto.getId());

            if (Objects.isNull(product)) {
                throw new RuntimeException("Product with id: " + productDto.getId() + " not found");
            }

            order.addProduct(product);
        });

        return new OrderEntityToDto(orderRepository.save(order)).build();
    }

    @Override
    public OrderDto update(final Integer id, final OrderStatus orderStatus) {
        final Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            order.get().setOrderStatus(orderStatus);
            return new OrderEntityToDto(orderRepository.save(order.get())).build();
        }

        return null;
    }

    @Override
    public OrderDto findById(final Integer id) {
        final Optional<Order> order = orderRepository.findById(id);

        return order.map(order1 -> new OrderEntityToDto(order1).build()).orElse(null);
    }

    @Override
    public List<OrderDto> findAllByClientId(final Integer id) {
        final List<Order> orders = orderRepository.findAllByClientId(id);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentId(final String id) {
        final List<Order> orders = orderRepository.findAllByEstablishmentId(id);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentIdAndDateBetween(final String id, final Date start, final Date end) {
        final List<Order> orders = orderRepository.findAllByEstablishmentIdAndCreatedAtBetween(id, start, end);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }

    @Override
    public List<OrderDto> findAllByEstablishmentIdAndDateWithStatus(
            final String id, final Date date, final OrderStatus orderStatus) {
        final List<Order> orders = orderRepository.findAllByEstablishmentIdAndCreatedAtAndOrderStatus(id, date, orderStatus);
        final List<OrderDto> ordersDto = new ArrayList<>();

        orders.forEach(order -> ordersDto.add(new OrderEntityToDto(order).build()));

        return ordersDto;
    }
}
