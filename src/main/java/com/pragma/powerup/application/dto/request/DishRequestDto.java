package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishRequestDto extends ObjectRequestDto{

    @NotBlank(message = "La descripcion es requerida")
    private String description;

    @Range(min = 01, message = "EL precio debe ser positivo y mayor a 0")
    @NotNull(message = "El precio es requerido")
    private double price;

    @NotBlank(message = "La URL de la imagen es requerida")
    private String imageUrl;

    @NotNull(message = "El id de la categoria es requerido")
    private long categoryId;

    @NotNull(message = "El id del restaurante es requerido")
    private long restaurantId;
}
