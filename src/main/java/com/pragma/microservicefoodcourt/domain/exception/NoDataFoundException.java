package com.pragma.microservicefoodcourt.domain.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
