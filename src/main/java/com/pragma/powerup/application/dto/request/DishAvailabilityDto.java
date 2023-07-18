package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishAvailabilityDto {
    @NotNull(message = "El id de la categoria es requerido")
    private Long id;

    @NotNull(message = "True o false es requerid")
    private boolean active;
}
