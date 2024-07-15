package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.api.IVerificationServicePort;
import com.pragma.microservicefoodcourt.domain.api.usecase.OrderUseCase;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.ITraceabilityApiPort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.TraceabilityApiAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.TwilioVerificationServiceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.ITraceabilityFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.mapper.ITraceabilityDtoMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter.OrderPersistenceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IOrderDishRepository;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderBeanConfiguration {
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantBeanConfiguration restaurantBeanConfiguration;
    private final DishBeanConfiguration dishBeanConfiguration;
    private final ApplicationBeanConfiguration applicationBeanConfiguration;

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderPersistenceAdapter(orderRepository, orderDishRepository, orderEntityMapper, restaurantEntityMapper);
    }

    @Bean
    public IVerificationServicePort verificationServicePort() {
        return new TwilioVerificationServiceAdapter();
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(
                orderPersistencePort(),
                restaurantBeanConfiguration.restaurantServicePort(),
                dishBeanConfiguration.dishServicePort(),
                applicationBeanConfiguration.userApiPort(),
                verificationServicePort(),
                applicationBeanConfiguration.traceabilityApiPort()
        );
    }
}
