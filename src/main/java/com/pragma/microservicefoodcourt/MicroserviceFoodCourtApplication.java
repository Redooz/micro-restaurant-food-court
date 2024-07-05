package com.pragma.microservicefoodcourt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceFoodCourtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceFoodCourtApplication.class, args);
    }

}
