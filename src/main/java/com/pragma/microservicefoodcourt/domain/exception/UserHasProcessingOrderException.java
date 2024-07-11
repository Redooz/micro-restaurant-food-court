package com.pragma.microservicefoodcourt.domain.exception;

public class UserHasProcessingOrderException extends RuntimeException {
    public UserHasProcessingOrderException(String message) {
        super(message);
    }
}
