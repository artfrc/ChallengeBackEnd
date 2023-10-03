package com.challengebackend.challengebackend.infra.exceptions;

public class ValidationException extends RuntimeException {
    
    public ValidationException(String message) {
        super(message);
    }

}
