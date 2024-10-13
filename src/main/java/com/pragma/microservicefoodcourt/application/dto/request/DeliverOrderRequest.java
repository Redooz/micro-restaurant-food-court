package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.constant.OrderReqConstant;
import com.pragma.microservicefoodcourt.application.constant.RequestConstant;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Builder
public record DeliverOrderRequest(
        @Pattern(regexp = RequestConstant.REGEX_ONLY_NUMBERS, message = OrderReqConstant.FORMAT_CODE_ERROR)
        @Length(min = OrderReqConstant.CODE_LENGTH, max = OrderReqConstant.CODE_LENGTH, message = OrderReqConstant.MSG_CODE_LENGTH)
        String code
) {
}
