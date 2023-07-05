package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto extends ObjectResponseDto{
    private String description;
    private double price;
    private String imageUrl;
    private boolean isActive;
    private long categoryId;
    private long restaurantId;
}
