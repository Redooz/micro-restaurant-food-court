package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.constant.DishConstant;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
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

    @Override
    public void updateDish(Dish updatedDish) {
        Dish originalDish = dishPersistencePort.findDishById(updatedDish.getId())
                .orElseThrow(
                        () -> new NoDataFoundException(String.format(DishConstant.DISH_NOT_FOUND, updatedDish.getId()))
                );

        Double price = (updatedDish.getPrice() != null) ? updatedDish.getPrice() : originalDish.getPrice();
        String description = (updatedDish.getDescription() != null) ? updatedDish.getDescription() : originalDish.getDescription();

        originalDish.setPrice(price);
        originalDish.setDescription(description);

        dishPersistencePort.updateDish(originalDish);
    }
}
