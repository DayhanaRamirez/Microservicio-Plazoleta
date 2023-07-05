package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto extends ObjectResponseDto {
    private int nit;
    private String address;
    private String telephone;
    private String logoUrl;
    private Long userId;
}
