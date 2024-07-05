package com.pragma.microservicefoodcourt.domain.exception;

import com.pragma.microservicefoodcourt.domain.constant.RestaurantConstant;

public class RestaurantOwnerNotFoundException extends RuntimeException {
    public RestaurantOwnerNotFoundException() {
        super(RestaurantConstant.USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
