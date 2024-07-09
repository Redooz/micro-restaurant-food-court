package com.pragma.microservicefoodcourt.domain.api.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.builder.DishBuilder;
import com.pragma.microservicefoodcourt.domain.builder.RestaurantBuilder;
import com.pragma.microservicefoodcourt.domain.builder.UserBuilder;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.exception.PermissionDeniedException;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
    @DisplayName("Should save dish when restaurant and category exist and user is owner")
    void shouldSaveDishWhenRestaurantAndCategoryExistAndUserIsOwner() {
        Restaurant restaurant = new RestaurantBuilder().setOwnerId("ownerId").createRestaurant();
        Category category = new Category(1L, "Test Category", "Test Description");
        User owner = new UserBuilder().setDocumentId(restaurant.getOwnerId()).createUser();

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(categoryServicePort.findCategoryById(category.getId())).thenReturn(category);

        Dish dish = new DishBuilder().setRestaurant(restaurant).setCategory(category).createDish();

        dishUseCase.saveDish(dish, owner);

        verify(restaurantServicePort, times(1)).findRestaurantByNit(dish.getRestaurant().getNit());
        verify(categoryServicePort, times(1)).findCategoryById(dish.getCategory().getId());
        verify(dishPersistencePort, times(1)).saveDish(dish);
    }

    @Test
    @DisplayName("Should not save dish when user is not owner")
    void shouldNotSaveDishWhenUserIsNotOwner() {
        Restaurant restaurant = new RestaurantBuilder().setOwnerId("ownerId").createRestaurant();
        Category category = new Category(1L, "Test Category", "Test Description");
        User nonOwner = new UserBuilder().setDocumentId("differentOwnerId").createUser();

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(categoryServicePort.findCategoryById(category.getId())).thenReturn(category);

        Dish dish = new DishBuilder().setRestaurant(restaurant).setCategory(category).createDish();

        assertThrows(PermissionDeniedException.class, () -> dishUseCase.saveDish(dish, nonOwner));
        verify(dishPersistencePort, times(0)).saveDish(dish);
    }

    @Test
    @DisplayName("Should update dish when dish exists and user is owner")
    void shouldUpdateDishWhenDishExistsAndUserIsOwner() {
        Dish originalDish = new DishBuilder()
                .setRestaurant(
                        new RestaurantBuilder()
                                .setOwnerId("ownerId")
                                .createRestaurant()
                )
                .createDish();
        Dish updatedDish = new DishBuilder()
                .setId(1L)
                .setPrice(100.0)
                .setDescription("Desc")
                .createDish();
        User owner = new UserBuilder().setDocumentId(originalDish.getRestaurant().getOwnerId()).createUser();
        Restaurant restaurant = new RestaurantBuilder().setOwnerId(owner.getDocumentId()).createRestaurant();

        when(dishPersistencePort.findDishById(updatedDish.getId())).thenReturn(Optional.of(originalDish));
        when(restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit())).thenReturn(restaurant);

        dishUseCase.updateDish(updatedDish, owner);

        verify(dishPersistencePort, times(1)).updateDish(originalDish);
    }

    @Test
    @DisplayName("Should not update dish when user is not owner")
    void shouldNotUpdateDishWhenUserIsNotOwner() {
        Dish originalDish = new DishBuilder()
                .setRestaurant(
                        new RestaurantBuilder()
                                .setOwnerId("ownerId")
                                .createRestaurant()
                )
                .createDish();
        Dish updatedDish = new DishBuilder()
                .setId(1L)
                .setPrice(100.0)
                .setDescription("Desc")
                .createDish();
        User nonOwner = new UserBuilder().setDocumentId("differentOwnerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setOwnerId(originalDish.getRestaurant().getOwnerId()).createRestaurant();

        when(dishPersistencePort.findDishById(updatedDish.getId())).thenReturn(Optional.of(originalDish));
        when(restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit())).thenReturn(restaurant);

        assertThrows(PermissionDeniedException.class, () -> dishUseCase.updateDish(updatedDish, nonOwner));
        verify(dishPersistencePort, times(0)).updateDish(any());
    }

    @Test
    @DisplayName("Should not update dish when dish does not exist")
    void shouldNotUpdateDishWhenDishDoesNotExist() {
        Dish updatedDish = new DishBuilder()
                .setId(1L)
                .setPrice(100.0)
                .setDescription("Desc")
                .createDish();
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();

        when(dishPersistencePort.findDishById(updatedDish.getId())).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> dishUseCase.updateDish(updatedDish, owner));
        verify(dishPersistencePort, times(0)).updateDish(any());
    }

    @Test
    @DisplayName("Should update dish and keep original price when updated price is null")
    void shouldUpdateDishAndKeepOriginalPriceWhenUpdatedPriceIsNull() {
        Dish originalDish = new DishBuilder()
                .setId(1L)
                .setPrice(50.0)
                .setDescription("Original description")
                .setRestaurant(
                        new RestaurantBuilder()
                                .setOwnerId("ownerId")
                                .createRestaurant()
                )
                .createDish();
        Dish updatedDish = new DishBuilder()
                .setId(1L)
                .setPrice(null)
                .setDescription("Updated description")
                .createDish();
        User owner = new UserBuilder().setDocumentId(originalDish.getRestaurant().getOwnerId()).createUser();
        Restaurant restaurant = new RestaurantBuilder().setOwnerId(owner.getDocumentId()).createRestaurant();

        when(dishPersistencePort.findDishById(updatedDish.getId())).thenReturn(Optional.of(originalDish));
        when(restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit())).thenReturn(restaurant);

        dishUseCase.updateDish(updatedDish, owner);

        assertEquals(50.0, originalDish.getPrice());
        assertEquals("Updated description", originalDish.getDescription());

        verify(dishPersistencePort, times(1)).updateDish(originalDish);
    }

    @Test
    @DisplayName("Should update dish and keep original description when updated description is null")
    void shouldUpdateDishAndKeepOriginalDescriptionWhenUpdatedDescriptionIsNull() {
        Dish originalDish = new DishBuilder()
                .setId(1L)
                .setPrice(50.0)
                .setDescription("Original description")
                .setRestaurant(
                        new RestaurantBuilder()
                                .setOwnerId("ownerId")
                                .createRestaurant()
                )
                .createDish();
        Dish updatedDish = new DishBuilder()
                .setId(1L)
                .setPrice(100.0)
                .setDescription(null)
                .createDish();
        User owner = new UserBuilder().setDocumentId(originalDish.getRestaurant().getOwnerId()).createUser();
        Restaurant restaurant = new RestaurantBuilder().setOwnerId(owner.getDocumentId()).createRestaurant();

        when(dishPersistencePort.findDishById(updatedDish.getId())).thenReturn(Optional.of(originalDish));
        when(restaurantServicePort.findRestaurantByNit(originalDish.getRestaurant().getNit())).thenReturn(restaurant);

        dishUseCase.updateDish(updatedDish, owner);

        assertEquals(100.0, originalDish.getPrice());
        assertEquals("Original description", originalDish.getDescription());

        verify(dishPersistencePort, times(1)).updateDish(originalDish);
    }
}
