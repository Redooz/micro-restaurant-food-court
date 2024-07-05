package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.RequestConstant;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
public record CreateRestaurantRequest(
        @NotBlank(message = RequestConstant.MSG_NIT_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_ONLY_NUMBERS, message = RequestConstant.MSG_NIT_NUMBER_FORMAT)
        String nit,

        @NotBlank(message = RequestConstant.MSG_ADDRESS_REQUIRED)
        String address,

        @NotBlank(message = RequestConstant.MSG_NAME_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_NAME)
        String name,

        @NotBlank(message = RequestConstant.MSG_OWNER_ID_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_ONLY_NUMBERS, message = RequestConstant.MSG_OWNER_ID_NUMBER_FORMAT)
        String ownerId,

        @NotBlank(message = RequestConstant.MSG_PHONE_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_PHONE, message = RequestConstant.MSG_PHONE_FORMAT)
        String phone,

        @NotBlank(message = RequestConstant.MSG_URL_LOGO_REQUIRED)
        @Pattern(regexp = RequestConstant.REGEX_URL_LOGO, message = RequestConstant.MSG_URL_LOGO_FORMAT)
        String urlLogo
) {
}
