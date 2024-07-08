package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.DishReqConstant;
import com.pragma.microservicefoodcourt.application.constant.RequestConstant;
import com.pragma.microservicefoodcourt.application.dto.CategoryItemDto;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
public record CreateDishRequest(
        @NotBlank(message = DishReqConstant.MSG_NAME_IS_REQUIRED)
        String name,

        @NotBlank(message = DishReqConstant.MSG_PRICE_IS_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_POSITIVE_NUMBER, message = DishReqConstant.MSG_PRICE_FORMAT)
        Double price,

        @NotBlank(message = DishReqConstant.MSG_DESCRIPTION_IS_REQUIRED)
        String description,

        @NotBlank(message = DishReqConstant.MSG_URL_IMAGE_IS_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_URL, message = DishReqConstant.MSG_URL_IMAGE_FORMAT)
        String urlImage,

        CategoryItemDto category,

        @NotBlank(message = DishReqConstant.MSG_RESTAURANT_NIT_IS_REQUIRED)
        String restaurantNIT
) {
}
