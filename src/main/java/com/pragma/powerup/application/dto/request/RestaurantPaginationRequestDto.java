package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantPaginationRequestDto {

    @NotNull(message = "El número de página es requerido")
    private int pageNumber;

    @NotNull(message = "El tamaño de página es requerido")
    private int pageSize;
}
