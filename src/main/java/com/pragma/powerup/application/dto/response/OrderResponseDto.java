package com.pragma.powerup.application.dto.response;

import java.time.LocalDate;

public class OrderResponseDto {
    private Long id;
    private Long idClient;
    private LocalDate date;
    private String status;
    private Long idChef;

    private Long idRestaurant;
}
