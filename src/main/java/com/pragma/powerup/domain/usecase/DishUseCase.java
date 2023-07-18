package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.ForbiddenUserException;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.client.IAccountFeignClient;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IAccountFeignClient accountFeignClient;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IAccountFeignClient accountFeignClient){
        this.dishPersistencePort = dishPersistencePort;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    public void saveDish(Dish dish, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        dishPersistencePort.saveDish(dish, token);
    }

    @Override
    public List<Dish> getAllDishes(String token) {
        if(verifyAdmin(token)) {
            throw new ForbiddenUserException();
        }
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public Dish getDish(Long id, String token) {
        if(verifyAdmin(token)){
            if(verifyId(token)){
                throw new ForbiddenUserException();
            }
            return dishPersistencePort.getDish(id, token);
        }
        return dishPersistencePort.getDish(id);
    }

    @Override
    public Dish getDish(Long id) {
        return dishPersistencePort.getDish(id);
    }

    @Override
    public void updateDish(Dish dish, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        dishPersistencePort.updateDish(dish, token);
    }

    @Override
    public void deleteDish(Long id, String token) {
        if(verifyAdmin(token)){
            if(verifyId(token)){
                throw new ForbiddenUserException();
            }
            dishPersistencePort.deleteDish(id, token);
        }
        dishPersistencePort.deleteDish(id);
    }

    @Override
    public void updateDishAvailability(Dish dish, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        dishPersistencePort.updateDishAvailability(dish, token);

    }

    @Override
    public List<DishPaginationResponseDto> getAllDishesByCategory(DishPaginationRequestDto dishPaginationRequestDto) {
        return dishPersistencePort.getAllDishesByCategory(dishPaginationRequestDto);
    }

    private boolean verifyId(String token){
        Long idRole = accountFeignClient.getUserRole(token);
        if(idRole == 3 || idRole == 4){
            return true;
        }
        return false;
    }

    private boolean verifyAdmin(String token){
        Long idRole = accountFeignClient.getUserRole(token);
        if(idRole != 1){
            return true;
        }
        return false;
    }
}
