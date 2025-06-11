package com.example.newsfeedproject.domain.user.constraint;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmailFormatValidationException extends RuntimeException {
    public EmailFormatValidationException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Object exception(Exception e) {
        return e.getMessage();
    }

}
