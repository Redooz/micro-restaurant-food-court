package com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter;

import com.pragma.microservicefoodcourt.domain.spi.IVerificationApiPort;
import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IVerificationFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.SendVerificationCodeRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.VerifyCodeRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TwilioVerificationApiAdapter implements IVerificationApiPort {
    private final IVerificationFeignClient verificationFeignClient;

    @Override
    public void sendCode(String phone, NotificationMethod method) {
        SendVerificationCodeRequest request = new SendVerificationCodeRequest(phone, method);

        verificationFeignClient.sendCode(request);
    }

    @Override
    public void verifyCode(String phone, String code) {
        VerifyCodeRequest request = new VerifyCodeRequest(phone, code);

        verificationFeignClient.verifyCode(request);
    }
}
