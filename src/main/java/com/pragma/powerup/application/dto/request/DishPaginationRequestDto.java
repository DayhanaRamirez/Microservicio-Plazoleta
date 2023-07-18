package com.pragma.powerup.application.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishPaginationRequestDto {

    @NotNull(message = "El id del restaurante es requerido")
    private Long idRestaurant;

    @NotNull(message = "El id de la categoria es requerido")
    private Long idCategory;

    @NotNull(message = "El número de página es requerido")
    private int pageNumber;

    @NotNull(message = "El tamaño de página es requerido")
    private int pageSize;

}
