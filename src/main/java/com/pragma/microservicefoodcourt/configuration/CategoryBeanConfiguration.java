package com.pragma.microservicefoodcourt.configuration;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.usecase.CategoryUseCase;
import com.pragma.microservicefoodcourt.domain.spi.ICategoryPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter.CategoryPersistenceAdapter;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
