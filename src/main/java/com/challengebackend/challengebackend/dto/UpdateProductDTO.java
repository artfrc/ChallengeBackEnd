package com.challengebackend.challengebackend.dto;

import com.dependenceapi.domain.User;

import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(

    @NotNull(message = "Empty id field")
    Long id,

    Double price,

    User user

) {
    
}