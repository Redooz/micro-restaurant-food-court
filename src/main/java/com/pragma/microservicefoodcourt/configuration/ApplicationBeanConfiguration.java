package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.spi.ITraceabilityApiPort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.TraceabilityApiAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.UserApiAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.ITraceabilityFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IUserFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.ITraceabilityDtoMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.IUserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationBeanConfiguration {
    private final IUserFeignClient userFeignClient;
    private final IUserDtoMapper userDtoMapper;
    private final ITraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityDtoMapper traceabilityDtoMapper;

    public IUserApiPort userApiPort() {
        return new UserApiAdapter(userFeignClient, userDtoMapper);
    }

    public ITraceabilityApiPort traceabilityApiPort() {
        return new TraceabilityApiAdapter(traceabilityFeignClient, traceabilityDtoMapper);
    }
}
