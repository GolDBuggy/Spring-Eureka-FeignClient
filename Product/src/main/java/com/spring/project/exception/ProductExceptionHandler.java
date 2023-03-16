package com.spring.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExc(UserNotFoundException exc){
        return new ResponseEntity<>(new ErrorResponse
                (exc.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now().toString()),
                HttpStatus.NOT_FOUND);
    }
}
