package com.pragma.microservicefoodcourt.domain.constant;

public class RestaurantConstant {
    private RestaurantConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESTAURANT_NOT_FOUND_EXCEPTION_MESSAGE = "Restaurant with the nit %s was not found.";
    public static final String NOT_OWNER_EXCEPTION_MESSAGE = "User with id %s is not an owner";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with the given id was not found.";
    public static final String EMPTY_RESTAURANT_LIST_EXCEPTION_MESSAGE = "No restaurant was found.";
}
