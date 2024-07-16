package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;

public interface IVerificationApiPort {
    void sendCode(String phone, NotificationMethod method);

    void verifyCode(String phone, String code);
}
