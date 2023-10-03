package com.challengebackend.challengebackend.dto;

import jakarta.validation.constraints.NotNull;

public record PurchasedDTO(
    
    @NotNull(message = "Empty user_fk field")
    Long user_fk,

    @NotNull(message = "Empty product_fk field")
    Long product_fk


) {
    
}
