package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishes();

    Dish getDish(Long id);

    void updateDish(Dish dish);

    void deleteDish(Long id);
}
