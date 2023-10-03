package com.challengebackend.challengebackend.dto;

import java.util.List;

public record ArgumentNotValidDTO(List<String> messages, String status) {
    
}
