package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final ICategoryServicePort categoryServicePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantServicePort restaurantServicePort, ICategoryServicePort categoryServicePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
        this.categoryServicePort = categoryServicePort;
    }

    @Override
    public void saveDish(Dish dish) {
        // Check if the restaurant exists
        restaurantServicePort.findRestaurantByNit(dish.getRestaurant().getNit());

        // Check if the category exists
        categoryServicePort.findCategoryById(dish.getCategory().getId());

        dishPersistencePort.saveDish(dish);
    }
}
