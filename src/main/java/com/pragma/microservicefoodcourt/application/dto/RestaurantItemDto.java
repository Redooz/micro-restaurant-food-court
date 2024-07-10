package com.pragma.microservicefoodcourt.application.dto;

import com.pragma.microservicefoodcourt.application.constant.OrderReqConstant;

import javax.validation.constraints.NotBlank;

public record RestaurantItemDto(
        @NotBlank(message = OrderReqConstant.MSG_NIT_NOT_BLANK)
        String nit
) {
}
