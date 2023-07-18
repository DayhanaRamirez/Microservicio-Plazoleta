package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishPaginationResponseDto {
    private String name;
    private String description;
    private double price;
    private String imageUrl;
}
