package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.UserApiAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IUserFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationBeanConfiguration {
    private final IUserFeignClient userFeignClient;
    private final IUserDtoMapper userDtoMapper;

    public IUserApiPort userApiPort() {
        return new UserApiAdapter(userFeignClient, userDtoMapper);
    }
}
