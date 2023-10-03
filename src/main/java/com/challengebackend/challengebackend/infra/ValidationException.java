package com.challengebackend.challengebackend.infra;

public class ValidationException extends RuntimeException {
    
    public ValidationException(String message) {
        super(message);
    }

}
