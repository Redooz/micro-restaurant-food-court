package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.api.usecase.OrderUseCase;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter.OrderPersistenceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IOrderEntityMapper;
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
    private final RestaurantBeanConfiguration restaurantBeanConfiguration;
    private final DishBeanConfiguration dishBeanConfiguration;

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderPersistenceAdapter(orderRepository, orderDishRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(
                orderPersistencePort(),
                restaurantBeanConfiguration.restaurantServicePort(),
                dishBeanConfiguration.dishServicePort()
        );
    }
}
