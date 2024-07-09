package com.pragma.microservicefoodcourt.domain.exception;

public class DishIsNotFromRestaurantException extends RuntimeException {
    public DishIsNotFromRestaurantException(String restaurantId) {
        super(String.format("Some dishes are not from restaurant with nit %s", restaurantId));
    }
}
