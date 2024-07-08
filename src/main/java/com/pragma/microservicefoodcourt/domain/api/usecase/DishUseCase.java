package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.constant.CategoryConstant;
import com.pragma.microservicefoodcourt.domain.constant.RestaurantConstant;
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
        if (restaurantServicePort.findRestaurantByNit(dish.getRestaurant().getNit()).isEmpty()) {
            throw new NoDataFoundException(
                    String.format(RestaurantConstant.RESTAURANT_NOT_FOUND_EXCEPTION_MESSAGE, dish.getRestaurant())
            );
        }

        if (categoryServicePort.findCategoryById(dish.getCategory().getId()).isEmpty()) {
            throw new NoDataFoundException(
                    String.format(CategoryConstant.CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE, dish.getCategory().getId())
            );
        }

        dishPersistencePort.saveDish(dish);
    }
}
