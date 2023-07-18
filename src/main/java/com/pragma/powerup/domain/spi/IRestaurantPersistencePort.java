package com.pragma.powerup.domain.spi;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(Long id);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long id);

    List<RestaurantPaginationResponseDto> getAllRestaurantsByName(RestaurantPaginationRequestDto restaurantPaginationRequestDto);
}