package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishUpdateRequestDto {

    @NotNull(message = "El id de la categoria es requerido")
    private Long id;

    @NotNull(message = "El precio es requerido")
    private double price;

    @NotBlank(message = "La descripcion es requerida")
    private String description;
}
