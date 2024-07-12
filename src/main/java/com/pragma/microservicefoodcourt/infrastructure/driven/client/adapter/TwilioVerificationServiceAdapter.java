package com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter;

import com.pragma.microservicefoodcourt.domain.api.IVerificationServicePort;
import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;
import com.pragma.microservicefoodcourt.domain.model.enums.VerificationStatus;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class TwilioVerificationServiceAdapter implements IVerificationServicePort {
    @Value("${twilio.service-sid}")
    private String serviceSid;

    @Override
    public VerificationStatus notifyUser(String phone, NotificationMethod method) {
        Verification verification = Verification.creator(serviceSid, phone, method.name().toLowerCase())
                .create();

        return VerificationStatus.valueOf(verification.getStatus().toUpperCase());
    }

    @Override
    public VerificationStatus verifyCode(String phone, String code) {
        VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                .setTo(phone)
                .setCode(code)
                .create();

        return VerificationStatus.valueOf(verificationCheck.getStatus().toUpperCase());
    }
}
