package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Optional<Dish> findDishById(Long id);
    void updateDish(Dish dish);
}
