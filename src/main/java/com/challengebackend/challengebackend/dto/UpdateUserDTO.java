package com.challengebackend.challengebackend.dto;

import jakarta.validation.constraints.NotNull;

import com.challengebackend.challengebackend.domain.Product;

public record UpdateUserDTO(

    @NotNull(message = "Empty id field")
    Long id,

    Double balance,

    Boolean active,

    Product product

) {
    
}
