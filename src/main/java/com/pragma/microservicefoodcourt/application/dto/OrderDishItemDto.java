package com.pragma.microservicefoodcourt.application.dto;

import com.pragma.microservicefoodcourt.application.constant.OrderReqConstant;

import javax.validation.constraints.Positive;

public record OrderDishItemDto(
        DishItemDto dish,

        @Positive(message = OrderReqConstant.MSG_QUANTITY_MUST_BE_POSITIVE)
        int quantity
) {
}