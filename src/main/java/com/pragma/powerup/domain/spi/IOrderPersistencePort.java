package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Order order, List<OrderDish> orderDishList);

    List<Order> getAllOrders(String token);

}
