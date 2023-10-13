package com.challengebackend.challengebackend.dto;

import com.challengebackend.challengebackend.domain.User;

import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(

    @NotNull(message = "Empty id field")
    Long id,

    Double price,

    User user

) {
    
}