package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryRequestDto extends ObjectRequestDto{

    @NotBlank(message = "La descripcion es requerida")
    private String description;
}
