package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.domain.exception.ForbiddenUserException;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.client.IAccountFeignClient;
import com.pragma.powerup.infrastructure.exception.DishNotFoundException;
import com.pragma.powerup.infrastructure.exception.ForbiddenOwnerException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import jdk.jfr.consumer.RecordedStackTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final ICategoryRepository categoryRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IAccountFeignClient accountFeignClient;

    @Override
    public void saveDish(Dish dish, String token) {

       Long[] data = accountFeignClient.getUserIdAndRole(token);
       RestaurantEntity restaurantEntity = restaurantRepository.getReferenceById(dish.getRestaurantId());
        if(!Objects.equals(restaurantEntity.getUserId(), data[0])){
            throw new ForbiddenUserException();

        }
        DishEntity dishEntity = dishEntityMapper.dishToEntity(dish);
        dishEntity.setRestaurantEntity(restaurantEntity);
        dishEntity.setCategoryEntity(categoryRepository.getReferenceById(dish.getCategoryId()));
        dishRepository.save(dishEntity);
    }

    @Override
    public List<Dish> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();
        if(dishEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return dishEntityMapper.entitiesToDishList(dishEntityList);
    }

    @Override
    public Dish getDish(Long id, String token) {
        Dish dish = getDish(id);
        Long[] data = accountFeignClient.getUserIdAndRole(token);
        RestaurantEntity restaurantEntity = restaurantRepository.getReferenceById(dish.getRestaurantId());
        if(!Objects.equals(restaurantEntity.getUserId(), data[0])){
            throw new ForbiddenOwnerException();
        }
        return dish;
    }

    @Override
    public Dish getDish(Long id) {
        DishEntity dishEntity = dishRepository.getReferenceById(id);
        Dish dish = dishEntityMapper.entityToDsh(dishRepository.getReferenceById(id));
        dish.setCategoryId(categoryEntityMapper.entityToCategory(dishEntity.getCategoryEntity()).getId());
        dish.setRestaurantId(restaurantEntityMapper.entityToRestaurant(dishEntity.getRestaurantEntity()).getId());
        return dish;
    }

    @Override
    public void updateDish(Dish dish, String token) {
        saveDish(dish, token);
    }

    @Override
    public void deleteDish(Long id, String token) {
        Long[] data = accountFeignClient.getUserIdAndRole(token);
        Dish dish = getDish(id);
        RestaurantEntity restaurantEntity = restaurantRepository.getReferenceById(dish.getRestaurantId());
        if(!Objects.equals(restaurantEntity.getUserId(), data[0])){
            throw new ForbiddenUserException();
        }
        dishRepository.deleteById(id);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public void updateDishAvailability(Dish dish, String token) {
        saveDish(dish, token);
    }

    @Override
    public List<DishPaginationResponseDto> getAllDishesByCategory(DishPaginationRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), Sort.by("name"));
        List<DishEntity> dishEntityList = dishRepository.fetchRestaurantCategoryDishData(dto.getIdRestaurant(), dto.getIdCategory(), pageable);
        return dishEntityMapper.entityToPagination(dishEntityList);
    }
}

