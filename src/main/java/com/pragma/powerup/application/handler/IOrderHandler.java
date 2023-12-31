package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {
    void  saveOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getAllOrders(String token);

}
