package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.usecase.DishUseCase;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter.DishPersistenceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DishBeanConfiguration {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final RestaurantBeanConfiguration restaurantBeanConfiguration;
    private final CategoryBeanConfiguration categoryBeanConfiguration;

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishPersistenceAdapter(dishRepository, dishEntityMapper, categoryBeanConfiguration.categoryEntityMapper());
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(
                dishPersistencePort(),
                restaurantBeanConfiguration.restaurantServicePort(),
                categoryBeanConfiguration.categoryServicePort()
        );
    }
}
