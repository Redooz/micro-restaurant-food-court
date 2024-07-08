package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Category;

import java.util.Optional;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Category findCategoryById(Long id);
}
