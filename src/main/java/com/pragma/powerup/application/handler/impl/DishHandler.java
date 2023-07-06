package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
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
    public void saveDish(DishRequestDto dishRequestDto) {
        Dish dish = objectRequestMapper.dishDtoToDish(dishRequestDto);
        dish.setActive(true);
        dishServicePort.saveDish(dish);
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        return objectResponseMapper.dishToDishDtoList(dishServicePort.getAllDishes());
    }

    @Override
    public DishResponseDto getDish(Long id) {
        return objectResponseMapper.dishToDishDto(dishServicePort.getDish(id));
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishUpdateRequestDto) {
        Dish dish = dishServicePort.getDish(dishUpdateRequestDto.getId());
        dish.setPrice(dishUpdateRequestDto.getPrice());
        dish.setDescription(dishUpdateRequestDto.getDescription());

        dishServicePort.updateDish(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishServicePort.deleteDish(id);
    }
}
