package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.Dish;

import java.util.List;
import java.util.Optional;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Optional<Dish> findDishById(Long id);
    void updateDish(Dish dish);
    List<Dish> findAllDishes(int page, int size);
    List<Dish> findAllDishesByCategory(Category category, int page, int size);
}
