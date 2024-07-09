package com.pragma.microservicefoodcourt.domain.exception;

public class UserHasProcessingOrderException extends RuntimeException {
    public UserHasProcessingOrderException(String documentId) {
        super(String.format("User with documentId %s has a processing order", documentId));
    }
}
