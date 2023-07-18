package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    @Query(value = "SELECT platos.nombre, platos.descripcion, platos.precio, platos.url_imagen" +
            "FROM platos" +
            "INNER JOIN restaurantes on platos.id_restaurante = restaurantes.id " +
            "INNER JOIN categorias on categorias.id = platos.id_categoria " +
            "where restaurantes.id = idRestaurant and platos.activo = true and categorias.id = idCategory", nativeQuery = true)
    List<DishEntity> fetchRestaurantCategoryDishData(@Param("idRestaurant") Long idRestaurant, @Param("idCategory") Long idCategory, Pageable pageable);
}
