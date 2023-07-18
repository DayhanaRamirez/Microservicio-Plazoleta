package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishAvailabilityDto;
import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequestDto dishRequestDto, String token);

    List<DishResponseDto> getAllDishes(String token);

    DishResponseDto getDish(Long id, String token);

    void updateDish(DishUpdateRequestDto dishUpdateRequestDto, String token);

    void deleteDish(Long id, String token);

    void updateDishAvailability(DishAvailabilityDto dishAvailabilityDto, String token);

    List<DishPaginationResponseDto> getAllDishesByCategory (DishPaginationRequestDto dishPaginationRequestDto);
}
