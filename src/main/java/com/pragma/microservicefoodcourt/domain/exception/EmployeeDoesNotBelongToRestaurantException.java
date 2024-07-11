package com.pragma.microservicefoodcourt.domain.exception;

public class EmployeeDoesNotBelongToRestaurantException extends RuntimeException {
    public EmployeeDoesNotBelongToRestaurantException(String message) {
        super(message);
    }
}
