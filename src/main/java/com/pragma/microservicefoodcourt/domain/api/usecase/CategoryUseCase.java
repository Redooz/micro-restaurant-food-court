package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.constant.CategoryConstant;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.spi.ICategoryPersistencePort;

import java.util.List;

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
    public Category findCategoryById(Long id) {
        return categoryPersistencePort.findCategoryById(id).orElseThrow(
                () -> new NoDataFoundException(
                        String.format(CategoryConstant.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE, id)
                )
        );
    }

    @Override
    public List<Category> findAllCategories(int page, int size) {
        List <Category> categories = categoryPersistencePort.findAllCategories(page, size);

        if (categories.isEmpty()) {
            throw new NoDataFoundException(CategoryConstant.CATEGORY_LIST_EMPTY_EXCEPTION_MESSAGE);
        }

        return categories;
    }
}
