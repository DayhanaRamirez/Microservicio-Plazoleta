package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantUpdateRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IObjectRequestMapper;
import com.pragma.powerup.application.mapper.IObjectResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token) {
        Restaurant restaurant = objectRequestMapper.restaurantDtoToRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant, token);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants(String token) {
        return objectResponseMapper.restaurantToRestaurantDtoList(restaurantServicePort.getAllRestaurants(token));
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long id, String token) {
        return objectResponseMapper.restaurantToRestaurantDto(restaurantServicePort.getRestaurant(id, token));
    }

    @Override
    public void updateRestaurant(RestaurantUpdateRequestDto restaurantUpdateRequestDto,String token) {
        Restaurant restaurant = restaurantServicePort.getRestaurant(restaurantUpdateRequestDto.getId());
        restaurant.setName(restaurantUpdateRequestDto.getName());
        restaurant.setNit(restaurantUpdateRequestDto.getNit());
        restaurant.setAddress(restaurantUpdateRequestDto.getAddress());
        restaurant.setTelephone(restaurantUpdateRequestDto.getTelephone());
        restaurant.setLogoUrl(restaurantUpdateRequestDto.getLogoUrl());
        restaurant.setUserId(restaurantUpdateRequestDto.getUserId());

        restaurantServicePort.updateRestaurant(restaurant, token);
    }

    @Override
    public void deleteRestaurant(Long id, String token) {
        restaurantServicePort.deleteRestaurant(id, token);
    }

    @Override
    public List<RestaurantPaginationResponseDto> getAllRestaurantsByName(RestaurantPaginationRequestDto restaurantPaginationRequestDto) {
        return restaurantServicePort.getAllRestaurantsByName(restaurantPaginationRequestDto);
    }

    private String trimToken(String token){
        if (token.startsWith("Bearer ")) {
            token = token.split(" ")[1].trim();
        }
        return token;
    }


}