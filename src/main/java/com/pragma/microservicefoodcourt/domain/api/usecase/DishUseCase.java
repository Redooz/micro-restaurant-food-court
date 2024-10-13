package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.constant.DishConstant;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.exception.PermissionDeniedException;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;

import java.util.List;

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
    public void saveDish(Dish dish, User loggedUser) {
        // Check if the restaurant exists
        Restaurant restaurant = restaurantServicePort.findRestaurantByNit(dish.getRestaurant().getNit());

        if (isNotOwner(loggedUser, restaurant)) {
            throw new PermissionDeniedException(String.format(DishConstant.PERMISSION_DENIED, "create"));
        }

        // Check if the category exists
        categoryServicePort.findCategoryById(dish.getCategory().getId());

        dish.setIsActive(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Dish updatedDish, User loggedUser) {
        Dish originalDish = this.findDishById(updatedDish.getId());

        Restaurant restaurant = restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit());

        if (isNotOwner(loggedUser, restaurant)) {
            throw new PermissionDeniedException(String.format(DishConstant.PERMISSION_DENIED, "update"));
        }

        Double price = (updatedDish.getPrice() != null) ? updatedDish.getPrice() : originalDish.getPrice();
        String description = (updatedDish.getDescription() != null) ? updatedDish.getDescription() : originalDish.getDescription();

        originalDish.setPrice(price);
        originalDish.setDescription(description);

        dishPersistencePort.updateDish(originalDish);
    }

    @Override
    public void updateActiveStatus(Long dishId, Boolean isActive, User loggedUser) {
        Dish originalDish = this.findDishById(dishId);

        Restaurant restaurant = restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit());

        if (isNotOwner(loggedUser, restaurant)) {
            throw new PermissionDeniedException(String.format(DishConstant.PERMISSION_DENIED, "update"));
        }

        originalDish.setIsActive(isActive);
        dishPersistencePort.updateDish(originalDish);
    }

    @Override
    public Dish findDishById(Long id) {
        return dishPersistencePort.findDishById(id)
                .orElseThrow(
                        () -> new NoDataFoundException(String.format(DishConstant.DISH_NOT_FOUND, id))
                );
    }

    @Override
    public List<Dish> findAllDishes(int page, int size) {
        List<Dish> dishes = dishPersistencePort.findAllDishes(page, size);

        if (dishes.isEmpty()) {
            throw new NoDataFoundException(DishConstant.EMPTY_DISHES_LIST_FOUND);
        }

        return dishes;
    }

    @Override
    public List<Dish> findAllDishesByCategory(Category category, int page, int size) {
        List<Dish> dishes = dishPersistencePort.findAllDishesByCategory(category, page, size);

        if (dishes.isEmpty()) {
            throw new NoDataFoundException(DishConstant.EMPTY_DISHES_LIST_FOUND);
        }

        return dishes;
    }

    private boolean isNotOwner(User loggedUser, Restaurant restaurant) {
        return !restaurant.getOwnerId().equals(loggedUser.getDocumentId());
    }
}
