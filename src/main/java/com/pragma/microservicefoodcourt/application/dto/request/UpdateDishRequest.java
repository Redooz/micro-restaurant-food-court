package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.DishReqConstant;
import lombok.Builder;

import javax.validation.constraints.Positive;

@Builder
public record UpdateDishRequest(
        @Positive(message = DishReqConstant.MSG_PRICE_FORMAT)
        Double price,

        String description
) {
}
