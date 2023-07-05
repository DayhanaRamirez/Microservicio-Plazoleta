package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishes();

    Dish getDish(Long id);

    void updateDish(Dish dish);

    void deleteDish(Long id);
}
