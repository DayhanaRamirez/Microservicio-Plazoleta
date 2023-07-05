package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantUpdateRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IObjectRequestMapper;
import com.pragma.powerup.application.mapper.IObjectResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        restaurantServicePort.saveRestaurant(objectRequestMapper.restaurantDtoToRestaurant(restaurantRequestDto));
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return objectResponseMapper.restaurantToRestaurantDtoList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long id) {
        return objectResponseMapper.restaurantToRestaurantDto(restaurantServicePort.getRestaurant(id));
    }

    @Override
    public void updateRestaurant(RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        Restaurant restaurant = restaurantServicePort.getRestaurant(restaurantUpdateRequestDto.getId());
        restaurant.setName(restaurantUpdateRequestDto.getName());
        restaurant.setNit(restaurantUpdateRequestDto.getNit());
        restaurant.setAddress(restaurantUpdateRequestDto.getAddress());
        restaurant.setTelephone(restaurantUpdateRequestDto.getTelephone());
        restaurant.setLogoUrl(restaurantUpdateRequestDto.getLogoUrl());
        restaurant.setUserId(restaurantUpdateRequestDto.getUserId());

        restaurantServicePort.updateRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantServicePort.deleteRestaurant(id);
    }
}