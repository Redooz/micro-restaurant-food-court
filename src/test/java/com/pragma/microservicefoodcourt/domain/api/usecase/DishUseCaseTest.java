package com.pragma.microservicefoodcourt.domain.api.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.builder.DishBuilder;
import com.pragma.microservicefoodcourt.domain.builder.RestaurantBuilder;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save dish when restaurant and category exist")
    void shouldSaveDishWhenRestaurantAndCategoryExist() {
        Restaurant restaurant = new RestaurantBuilder().createRestaurant();
        Category category = new Category(1L, "Test Category", "Test Description");

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(categoryServicePort.findCategoryById(category.getId())).thenReturn(category);

        Dish dish = new DishBuilder().setRestaurant(restaurant).setCategory(category).createDish();

        dishUseCase.saveDish(dish);

        verify(restaurantServicePort, times(1)).findRestaurantByNit(dish.getRestaurant().getNit());
        verify(categoryServicePort, times(1)).findCategoryById(dish.getCategory().getId());
        verify(dishPersistencePort, times(1)).saveDish(dish);
    }

    @Test
    @DisplayName("Should not save dish when restaurant does not exist")
    void shouldNotSaveDishWhenRestaurantDoesNotExist() {
        Restaurant restaurant = new RestaurantBuilder().createRestaurant();
        Category category = new Category(1L, "Test Category", "Test Description");
        Dish dish = new DishBuilder().setRestaurant(restaurant).setCategory(category).createDish();

        doThrow(new RuntimeException()).when(restaurantServicePort).findRestaurantByNit(dish.getRestaurant().getNit());

        assertThrows(RuntimeException.class, () -> dishUseCase.saveDish(dish));
        verify(dishPersistencePort, times(0)).saveDish(dish);
    }

    @Test
    @DisplayName("Should not save dish when category does not exist")
    void shouldNotSaveDishWhenCategoryDoesNotExist() {
        Restaurant restaurant = new RestaurantBuilder().createRestaurant();
        Category category = new Category(1L, "Test Category", "Test Description");
        Dish dish = new DishBuilder().setRestaurant(restaurant).setCategory(category).createDish();

        doThrow(new RuntimeException()).when(categoryServicePort).findCategoryById(dish.getCategory().getId());

        assertThrows(RuntimeException.class, () -> dishUseCase.saveDish(dish));
        verify(dishPersistencePort, times(0)).saveDish(dish);
    }
}