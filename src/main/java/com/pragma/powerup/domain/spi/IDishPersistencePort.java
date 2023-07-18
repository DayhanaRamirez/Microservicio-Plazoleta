package com.pragma.powerup.domain.spi;

import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish, String token);

    List<Dish> getAllDishes();

    Dish getDish(Long id, String token);

    Dish getDish(Long id);

    void updateDish(Dish dish, String token);

    void deleteDish(Long id, String token);

    void deleteDish(Long id);

    void updateDishAvailability(Dish dish, String token);

    List<DishPaginationResponseDto> getAllDishesByCategory (DishPaginationRequestDto dishPaginationRequestDto);
}
