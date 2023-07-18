package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    RestaurantEntity restaurantToEntity(Restaurant restaurant);
    Restaurant entityToRestaurant(RestaurantEntity restaurantEntity);

    List<Restaurant> entitiesToRestaurantList(List<RestaurantEntity> restaurantEntityList);

    List<RestaurantPaginationResponseDto> entityToPagination(List<RestaurantEntity> restaurantEntityList);
}
