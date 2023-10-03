package com.challengebackend.challengebackend.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.challengebackend.challengebackend.dto.ArgumentNotValidDTO;
import com.challengebackend.challengebackend.dto.ValidationExceptionDTO;

@RestControllerAdvice
public class HandleErrors {
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationExceptionDTO> handleDuplicateData(ValidationException ex) {
        ValidationExceptionDTO exceptionDTO = new ValidationExceptionDTO("Data entered already exists in the database!", "409");
        return ResponseEntity.status(409).body(exceptionDTO);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ValidationExceptionDTO> handleNotFound(NoSuchElementException ex) {
        ValidationExceptionDTO exceptionDTO = new ValidationExceptionDTO("Not found data!", "404");
        return ResponseEntity.status(404).body(exceptionDTO);
    }

    @ExceptionHandler(ValidationUpdateUserException.class)
    public ResponseEntity<ValidationExceptionDTO> handleUpdateUser(ValidationUpdateUserException ex) {
        ValidationExceptionDTO exceptionDTO = new ValidationExceptionDTO(ex.getMessage(), "406");
        return ResponseEntity.status(406).body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidDTO> handleInvalidData(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        ArgumentNotValidDTO argumentNotValidDTO = new ArgumentNotValidDTO(getListDefaultMessage(errors),"400");
        return ResponseEntity.badRequest().body(argumentNotValidDTO);
    }

    private List<String> getListDefaultMessage(List<FieldError> errors) {
        List<String> list = new ArrayList<>();
        errors.forEach(error -> {
            list.add(error.getDefaultMessage());
        });

        return list;
    }

}
