package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public void saveOrder(Order order, List<OrderDish> orderDishList) {
        OrderEntity orderEntity = orderRepository.save(orderEntityMapper.orderToEntity(order));
        orderRepository.flush();
        for(OrderDish var : orderDishList){
            var.setIdOrder(orderEntity.getId());
        }

        for(OrderDish var : orderDishList){
            OrderDishEntity orderDishEntity = orderDishEntityMapper.orderDishToEntity(var);
            orderDishRepository.save(orderDishEntity);
        }

    }

    @Override
    public List<Order> getAllOrders(String token) {
        return null;
    }
}
