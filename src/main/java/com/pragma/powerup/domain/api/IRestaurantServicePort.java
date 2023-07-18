package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant, String token);

    List<Restaurant> getAllRestaurants(String token);

    Restaurant getRestaurant(Long id, String token);

    Restaurant getRestaurant(Long id);

    void updateRestaurant(Restaurant restaurant, String token);

    void deleteRestaurant(Long id, String token);

    List<RestaurantPaginationResponseDto> getAllRestaurantsByName(RestaurantPaginationRequestDto restaurantPaginationRequestDto);
}