package com.challengebackend.challengebackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(

    @NotBlank(message = "Empty name field")
    String name,

    @NotNull(message = "Empty price field")
    Double price

) {
    
}
