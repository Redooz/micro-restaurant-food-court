package com.pragma.microservicefoodcourt.domain.constant;

public class DishConstant {
    private DishConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DISH_NOT_FOUND = "Dish with id %s not found";
    public static final String PERMISSION_DENIED = "You don't have permission to %s a dish for this restaurant";
}
