package com.user.spring.exception;

public class PasswordNotEqualsException extends RuntimeException{
    public PasswordNotEqualsException(String message) {
        super(message);
    }

    public PasswordNotEqualsException(String message, Throwable cause) {
        super(message, cause);
    }
}
