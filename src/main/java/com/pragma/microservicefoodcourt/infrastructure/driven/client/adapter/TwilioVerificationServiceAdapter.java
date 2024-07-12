package com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter;

import com.pragma.microservicefoodcourt.domain.api.IVerificationServicePort;
import com.pragma.microservicefoodcourt.domain.model.NotificationMethod;
import com.twilio.rest.verify.v2.service.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class TwilioVerificationServiceAdapter implements IVerificationServicePort {
    @Value("${twilio.service-sid}")
    private String serviceSid;

    @Override
    public String notifyUser(String phone, NotificationMethod method) {
        Verification verification = Verification.creator(serviceSid, phone, method.name().toLowerCase())
                .create();

        return verification.getStatus();
    }
}
