package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IObjectRequestMapper;
import com.pragma.powerup.application.mapper.IObjectResponseMapper;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        Order order = objectRequestMapper.orderDtoToOrder(orderRequestDto);
        order.setDate(LocalDate.now());
        order.setStatus("pendiente");

        List<OrderDish> orderDishList = new ArrayList<>();

        for(int i = 0; i < orderRequestDto.getIdDishes().length; i++){
            OrderDish orderDish = new OrderDish(orderRequestDto.getIdDishes()[i], orderRequestDto.getQuantity()[i]);
            orderDishList.add(orderDish);
        }

        orderServicePort.saveOrder(order, orderDishList);
    }

    @Override
    public List<OrderResponseDto> getAllOrders(String token) {
        return null;
    }
}
