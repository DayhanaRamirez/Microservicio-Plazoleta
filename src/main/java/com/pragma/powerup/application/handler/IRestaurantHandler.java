package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantUpdateRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token);

    List<RestaurantResponseDto> getAllRestaurants(String token);

    RestaurantResponseDto getRestaurant(Long id, String token);

    void updateRestaurant(RestaurantUpdateRequestDto restaurantUpdateRequestDto, String token);

    void deleteRestaurant(Long id, String token);

    List<RestaurantPaginationResponseDto> getAllRestaurantsByName(RestaurantPaginationRequestDto restaurantPaginationRequestDto);
}