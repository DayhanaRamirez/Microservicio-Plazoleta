package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(Long id);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long id);
}