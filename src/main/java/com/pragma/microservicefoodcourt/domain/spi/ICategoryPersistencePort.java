package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Category;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Optional<Category> findCategoryById(Long id);
}
