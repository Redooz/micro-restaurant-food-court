package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.User;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish, User loggedUser);
    void updateDish(Dish dish, User loggedUser);
    void updateActiveStatus(Long dishId, Boolean isActive, User loggedUser);
    Dish findDishById(Long id);
    List<Dish> findAllDishes(int page, int size);
    List<Dish> findAllDishesByCategory(Category category, int page, int size);
}
