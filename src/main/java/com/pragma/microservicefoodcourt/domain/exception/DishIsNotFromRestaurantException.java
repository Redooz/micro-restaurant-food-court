package com.pragma.microservicefoodcourt.domain.exception;

public class DishIsNotFromRestaurantException extends RuntimeException {
    public DishIsNotFromRestaurantException(String message) {
        super(message);
    }
}
