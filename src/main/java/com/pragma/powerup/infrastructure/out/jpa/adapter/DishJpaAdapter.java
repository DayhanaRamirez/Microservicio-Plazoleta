package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.DishNotFoundException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final ICategoryRepository categoryRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.dishToEntity(dish);
        dishEntity.setRestaurantEntity(restaurantRepository.getReferenceById(dish.getRestaurantId()));
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
    public Dish getDish(Long id) {
        DishEntity dishEntity = dishRepository.getReferenceById(id);
        Dish dish = dishEntityMapper.entityToDsh(dishRepository.getReferenceById(id));
        dish.setCategoryId(categoryEntityMapper.entityToCategory(dishEntity.getCategoryEntity()).getId());
        dish.setRestaurantId(restaurantEntityMapper.entityToRestaurant(dishEntity.getRestaurantEntity()).getId());
        return dish;
    }

    @Override
    public void updateDish(Dish dish) {
        saveDish(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
