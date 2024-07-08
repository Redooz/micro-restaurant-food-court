package com.pragma.microservicefoodcourt.application.dto;

import com.pragma.microservicefoodcourt.application.constant.CategoryReqConstant;

import javax.validation.constraints.NotBlank;

public record CategoryItemDto(
        @NotBlank(message = CategoryReqConstant.MSG_ID_IS_REQUIRED)
        Long id
) {
}
