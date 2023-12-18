package com.challengebackend.challengebackend.infra.exceptions;

public class ValidationExternalService extends RuntimeException {
    
    public ValidationExternalService(String message) {
        super(message);
    }

}
