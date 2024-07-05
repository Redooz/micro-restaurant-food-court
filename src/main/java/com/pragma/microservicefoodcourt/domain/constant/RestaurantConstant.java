package com.pragma.microservicefoodcourt.domain.constant;

public class RestaurantConstant {
    private RestaurantConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NOT_OWNER_EXCEPTION_MESSAGE = "User with id %s is not an owner";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with the given id was not found.";
}
