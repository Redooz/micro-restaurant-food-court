package com.pragma.microservicefoodcourt.domain.exception;

public class UserDoesNotOwnOrderException extends RuntimeException {
    public UserDoesNotOwnOrderException(String message) {
        super(message);
    }
}
