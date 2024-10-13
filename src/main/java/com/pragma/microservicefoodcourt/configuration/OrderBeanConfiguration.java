package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.spi.IVerificationApiPort;
import com.pragma.microservicefoodcourt.domain.api.usecase.OrderUseCase;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.adapter.TwilioVerificationApiAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IUserFeignClient;
import com.pragma.microservicefoodcourt.infrastructure.driven.client.api.IVerificationFeignClient;
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
    private final IVerificationFeignClient verificationFeignClient;

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderPersistenceAdapter(orderRepository, orderDishRepository, orderEntityMapper, restaurantEntityMapper);
    }

    @Bean
    public IVerificationApiPort verificationServicePort() {
        return new TwilioVerificationApiAdapter(verificationFeignClient);
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
