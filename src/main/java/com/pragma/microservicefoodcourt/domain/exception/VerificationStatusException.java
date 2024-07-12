package com.pragma.microservicefoodcourt.domain.exception;

public class VerificationStatusException extends RuntimeException {
    public VerificationStatusException(String message) {
        super(message);
    }
}
