package com.pragma.microservicefoodcourt.infrastructure.driven.client.api;

import com.pragma.microservicefoodcourt.configuration.client.FeignClientConfig;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.CreateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request.UpdateTraceabilityRequest;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response.GetTraceabilityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "traceability-food-court", url = "http://localhost:8082/traceability", configuration = FeignClientConfig.class)
public interface ITraceabilityFeignClient {
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createTraceability(CreateTraceabilityRequest request);

    @PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateTraceability(@PathVariable Long orderId, UpdateTraceabilityRequest request);

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GetTraceabilityResponse getTraceabilityByOrderId(@PathVariable Long orderId);
}
