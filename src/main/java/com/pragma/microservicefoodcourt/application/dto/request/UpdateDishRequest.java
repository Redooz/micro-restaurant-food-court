package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.DishReqConstant;

import javax.validation.constraints.Positive;

public record UpdateDishRequest(
        @Positive(message = DishReqConstant.MSG_PRICE_FORMAT)
        Double price,

        String description
) {
}
