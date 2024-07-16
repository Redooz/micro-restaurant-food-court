package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request;


import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;

public record SendVerificationCodeRequest(
        String phone,

        NotificationMethod notificationMethod
) {
}
