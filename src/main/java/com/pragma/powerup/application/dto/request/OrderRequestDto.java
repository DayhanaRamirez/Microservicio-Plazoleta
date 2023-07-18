package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "El id del restaurante es necesario")
    private Long idRestaurant;

    @NotNull(message = "El id del restaurante es necesario")
    private Long idClient;

    private Long[] idDishes;
    private int[] quantity;

}
