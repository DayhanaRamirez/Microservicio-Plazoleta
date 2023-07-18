package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectResponseMapper {

    RestaurantResponseDto restaurantToRestaurantDto(Restaurant restaurant);

    List<RestaurantResponseDto> restaurantToRestaurantDtoList(List<Restaurant> restaurantList);

    CategoryResponseDto categoryToCategoryDto(Category category);

    List<CategoryResponseDto> categoryToCategoryDtoList(List<Category> categoryList);

    DishResponseDto dishToDishDto(Dish dish);

    List<DishResponseDto> dishToDishDtoList(List<Dish> dishList);

    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> orderToOrderResponseDtoList(List<Order> orderList);
}
