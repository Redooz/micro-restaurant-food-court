package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.RequestConstant;
import com.pragma.microservicefoodcourt.application.constant.RestaurantReqConstant;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
public record CreateRestaurantRequest(
        @NotBlank(message = RestaurantReqConstant.MSG_NIT_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_ONLY_NUMBERS, message = RestaurantReqConstant.MSG_NIT_NUMBER_FORMAT)
        String nit,

        @NotBlank(message = RestaurantReqConstant.MSG_ADDRESS_REQUIRED)
        String address,

        @NotBlank(message = RestaurantReqConstant.MSG_NAME_REQUIRED)
        @Pattern(regexp = RestaurantReqConstant.REGEX_NAME)
        String name,

        @NotBlank(message = RestaurantReqConstant.MSG_OWNER_ID_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_ONLY_NUMBERS, message = RestaurantReqConstant.MSG_OWNER_ID_NUMBER_FORMAT)
        String ownerId,

        @NotBlank(message = RestaurantReqConstant.MSG_PHONE_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_PHONE, message = RestaurantReqConstant.MSG_PHONE_FORMAT)
        String phone,

        @NotBlank(message = RestaurantReqConstant.MSG_URL_LOGO_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_URL, message = RestaurantReqConstant.MSG_URL_LOGO_FORMAT)
        String urlLogo
) {
}
