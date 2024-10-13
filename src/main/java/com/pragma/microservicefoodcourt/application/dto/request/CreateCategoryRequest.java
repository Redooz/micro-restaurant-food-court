package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.CategoryReqConstant;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record CreateCategoryRequest(
        @NotBlank(message = CategoryReqConstant.MSG_NAME_IS_REQUIRED)
        String name,

        @NotBlank(message = CategoryReqConstant.MSG_DESCRIPTION_IS_REQUIRED)
        String description
) {
}
