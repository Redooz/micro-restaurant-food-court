package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.api.usecase.RestaurantUseCase;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter.RestaurantPersistenceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestaurantBeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final ApplicationBeanConfiguration applicationBeanConfiguration;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantPersistenceAdapter(restaurantRepository, restaurantEntityMapper);
    }


    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), applicationBeanConfiguration.userApiPort());
    }
}
