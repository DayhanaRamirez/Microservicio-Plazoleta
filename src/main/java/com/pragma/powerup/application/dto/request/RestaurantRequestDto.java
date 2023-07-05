package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantRequestDto{

    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]+$", message = "El nombre debe contener al menos una letra")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Pattern(regexp = "^[0-9]+$", message = "EL NIT solo debe tener números")
    @NotBlank(message = "El NIT es requerido")
    private String nit;

    @NotBlank(message = "La dirección es requerida")
    private String address;

    @NotBlank(message = "La teléfono es requerido")
    @Size(min = 10, max = 13, message = "El número debe tener entre 10 y 13 caracteres")
    @Pattern(regexp = "^(?:\\+?[0-9]+)$", message = "El telefono solo debe contener números y el signo +")
    private String telephone;

    @NotBlank(message = "La URL del logo es requerida")
    private String logoUrl;

    @NotNull(message = "El i del usuario es requerido")
    private Long userId;
}
