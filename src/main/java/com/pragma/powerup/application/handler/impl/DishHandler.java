package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishAvailabilityDto;
import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IObjectRequestMapper;
import com.pragma.powerup.application.mapper.IObjectResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto, String token) {
        Dish dish = objectRequestMapper.dishDtoToDish(dishRequestDto);
        dish.setActive(true);
        dishServicePort.saveDish(dish, trimToken(token));
    }

    @Override
    public List<DishResponseDto> getAllDishes(String token) {
        return objectResponseMapper.dishToDishDtoList(dishServicePort.getAllDishes(trimToken(token)));
    }

    @Override
    public DishResponseDto getDish(Long id, String token) {
        return objectResponseMapper.dishToDishDto(dishServicePort.getDish(id, trimToken(token)));
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto, String token) {
        Dish dish = dishServicePort.getDish(dishUpdateRequestDto.getId());
        dish.setPrice(dishUpdateRequestDto.getPrice());
        dish.setDescription(dishUpdateRequestDto.getDescription());

        dishServicePort.updateDish(dish, trimToken(token));
    }

    @Override
    public void deleteDish(Long id, String token) {
        dishServicePort.deleteDish(id, trimToken(token));
    }

    @Override
    public void updateDishAvailability(DishAvailabilityDto dishAvailabilityDto, String token) {
        Dish dish = dishServicePort.getDish(dishAvailabilityDto.getId());
        dish.setActive(dishAvailabilityDto.isActive());
        dishServicePort.updateDishAvailability(dish, trimToken(token));
    }

    @Override
    public List<DishPaginationResponseDto> getAllDishesByCategory(DishPaginationRequestDto dishPaginationRequestDto) {
        return dishServicePort.getAllDishesByCategory(dishPaginationRequestDto);
    }

    private String trimToken(String token){
        if (token.startsWith("Bearer ")) {
            token = token.split(" ")[1].trim();
        }
        return token;
    }
}
