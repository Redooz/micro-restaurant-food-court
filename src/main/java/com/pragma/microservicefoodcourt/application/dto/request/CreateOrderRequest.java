package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.OrderReqConstant;
import com.pragma.microservicefoodcourt.application.dto.OrderDishItemDto;
import com.pragma.microservicefoodcourt.application.dto.RestaurantItemDto;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
public record CreateOrderRequest(
        @NotEmpty(message = OrderReqConstant.MSG_ORDER_DISHES_NOT_EMPTY)
        List<OrderDishItemDto> orderDishes,

        RestaurantItemDto restaurant
) {
}
