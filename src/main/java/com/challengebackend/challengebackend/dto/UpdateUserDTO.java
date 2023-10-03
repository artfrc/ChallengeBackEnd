package com.challengebackend.challengebackend.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(

    @NotNull(message = "Empty id field")
    Long id,

    Double balance,

    Boolean active

) {
    
}
