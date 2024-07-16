package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request;

public record VerifyCodeRequest(
        String phone,

        String code
) {
}
