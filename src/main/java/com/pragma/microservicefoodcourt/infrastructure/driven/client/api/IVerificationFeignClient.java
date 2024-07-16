package com.pragma.microservicefoodcourt.infrastructure.driven.client.api;

import com.pragma.microservicefoodcourt.configuration.client.FeignClientConfig;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.SendVerificationCodeRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.VerifyCodeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "verification-food-court", url = "http://localhost:8083/verification", configuration = FeignClientConfig.class)
public interface IVerificationFeignClient {

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendCode(SendVerificationCodeRequest request);

    @PostMapping(value = "/verify", consumes = MediaType.APPLICATION_JSON_VALUE)
    void verifyCode(VerifyCodeRequest request);
}
