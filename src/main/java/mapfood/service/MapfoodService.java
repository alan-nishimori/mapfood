package mapfood.service;

import mapfood.model.MotoboyWithDistance;
import mapfood.model.Order;

public interface MapfoodService {

    Order createOrder(Order order);
    Order getOrder(Integer id);
    MotoboyWithDistance getClosestMotoboy(String EstablishmentId);
}
