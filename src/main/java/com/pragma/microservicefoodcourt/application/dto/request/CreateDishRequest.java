package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.DishReqConstant;
import com.pragma.microservicefoodcourt.application.constant.RequestConstant;
import com.pragma.microservicefoodcourt.application.dto.CategoryItemDto;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Builder
public record CreateDishRequest(
        @NotBlank(message = DishReqConstant.MSG_NAME_IS_REQUIRED)
        String name,

        @NotNull(message = DishReqConstant.MSG_PRICE_IS_REQUIRED)
        @Positive(message = DishReqConstant.MSG_PRICE_FORMAT)
        Double price,

        @NotBlank(message = DishReqConstant.MSG_DESCRIPTION_IS_REQUIRED)
        String description,

        @NotBlank(message = DishReqConstant.MSG_URL_IMAGE_IS_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_URL, message = DishReqConstant.MSG_URL_IMAGE_FORMAT)
        String urlImage,

        CategoryItemDto category,

        RestaurantItemDto restaurant
) {
}
