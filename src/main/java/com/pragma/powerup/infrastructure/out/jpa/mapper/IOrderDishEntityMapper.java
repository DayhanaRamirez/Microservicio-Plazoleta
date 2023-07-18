package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {
    OrderDishEntity orderDishToEntity(OrderDish orderDish);

    OrderDish entityToOrderDish(OrderDishEntity orderDishEntity);

    List<OrderDish> entitiesToOrderDishList(List<OrderDishEntity> orderDishEntityList);
}
