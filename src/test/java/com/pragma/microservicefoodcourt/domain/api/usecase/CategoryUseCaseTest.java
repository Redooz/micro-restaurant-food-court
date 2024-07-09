package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save category")
    void shouldSaveCategory() {
        Category category = new Category(1L, "Test", "Test");
        categoryUseCase.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    @DisplayName("Should find category by id")
    void shouldFindCategoryById() {
        Category category = new Category(100L, "Test", "Test");

        when(categoryPersistencePort.findCategoryById(100L)).thenReturn(Optional.of(category));

        Category result = categoryUseCase.findCategoryById(100L);

        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).findCategoryById(100L);
    }

    @Test
    @DisplayName("Should throw exception when category not found")
    void shouldThrowExceptionWhenCategoryNotFound() {
        when(categoryPersistencePort.findCategoryById(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> categoryUseCase.findCategoryById(1L));

        verify(categoryPersistencePort, times(1)).findCategoryById(1L);
    }

    @Test
    @DisplayName("Should find all categories")
    void shouldFindAllCategories() {
        List<Category> categories = List.of(
                new Category(1L, "Test", "Test"),
                new Category(2L, "Test", "Test")
        );
        when(categoryPersistencePort.findAllCategories(0, 10)).thenReturn(categories);

        categoryUseCase.findAllCategories(0, 10);
        verify(categoryPersistencePort, times(1)).findAllCategories(0, 10);
    }

    @Test
    @DisplayName("Should throw exception when category list is empty")
    void shouldThrowExceptionWhenCategoryListIsEmpty() {
        when(categoryPersistencePort.findAllCategories(0, 10)).thenReturn(List.of());

        assertThrows(NoDataFoundException.class, () -> categoryUseCase.findAllCategories(0, 10));

        verify(categoryPersistencePort, times(1)).findAllCategories(0, 10);
    }
}
