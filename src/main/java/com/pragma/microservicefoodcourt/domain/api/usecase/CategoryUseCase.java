package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.spi.ICategoryPersistencePort;

import java.util.Optional;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryPersistencePort.findCategoryById(id);
    }
}
