package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.ForbiddenUserException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.client.IAccountFeignClient;
import org.springframework.data.domain.Page;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    private final IAccountFeignClient accountFeignClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IAccountFeignClient accountFeignClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants(String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public Restaurant getRestaurant(Long id, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant, String token) {
//        if(verifyId(token)){
//            throw new ForbiddenUserException();
//        }
        restaurantPersistencePort.updateRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id, String token) {
        if(verifyId(token)){
            throw new ForbiddenUserException();
        }
        restaurantPersistencePort.deleteRestaurant(id);
    }

    @Override
    public List<RestaurantPaginationResponseDto> getAllRestaurantsByName(RestaurantPaginationRequestDto restaurantPaginationRequestDto) {
        return restaurantPersistencePort.getAllRestaurantsByName(restaurantPaginationRequestDto);
    }

    private boolean verifyId(String token){
        Long idRole = accountFeignClient.getUserRole(token);
        if(idRole != 1){
            return true;
        }
        return false;
    }
}

