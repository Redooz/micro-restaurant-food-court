package com.pragma.microservicefoodcourt.infrastructure.driven.client.api;

import com.pragma.microservicefoodcourt.configuration.client.FeignClientConfig;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response.GetUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-food-court", url = "http://localhost:8080/users", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    GetUserResponse findUserByDocumentId(@PathVariable String id);
}
