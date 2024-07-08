package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.DishReqConstant;

import javax.validation.constraints.NotBlank;

public record RestaurantItemDto (
        @NotBlank(message = DishReqConstant.MSG_RESTAURANT_NIT_IS_REQUIRED)
        String nit
) {
}
