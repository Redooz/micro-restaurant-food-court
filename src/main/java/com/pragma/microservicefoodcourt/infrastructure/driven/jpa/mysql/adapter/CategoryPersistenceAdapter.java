package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.spi.ICategoryPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryMapper.toEntity(category));
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDomain);
    }

    @Override
    public List<Category> findAllCategories(int page, int size) {
        // TODO: Implement pagination
        return List.of();
    }
}
