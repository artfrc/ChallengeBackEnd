package com.challengebackend.challengebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTO(

    @NotBlank(message = "Empty name field")
    String name,
    
    @NotBlank(message = "Empty document field")
    @Pattern(regexp = "\\d{11}", message = "Invalid format document, requires 11 digits")
    String document,

    @NotBlank(message = "Empty email field")
    @Email(message = "Invalid format email. (Example: example@gmail.com)")
    String email

) {
    
}
