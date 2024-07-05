package com.pragma.microservicefoodcourt.domain.exception;

import com.pragma.microservicefoodcourt.domain.constant.RestaurantConstant;

public class NotOwnerException extends RuntimeException {
    public NotOwnerException(String id) {
        super(String.format(RestaurantConstant.NOT_OWNER_EXCEPTION_MESSAGE, id));
    }
}