package com.pragma.microservicefoodcourt.domain.api.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.builder.DishBuilder;
import com.pragma.microservicefoodcourt.domain.builder.OrderBuilder;
import com.pragma.microservicefoodcourt.domain.builder.OrderDishBuilder;
import com.pragma.microservicefoodcourt.domain.builder.RestaurantBuilder;
import com.pragma.microservicefoodcourt.domain.builder.UserBuilder;
import com.pragma.microservicefoodcourt.domain.exception.DishIsNotFromRestaurantException;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.OrderDish;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IDishServicePort dishServicePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save order when all dishes are from the same restaurant and user has no processing order")
    void shouldSaveOrderWhenAllDishesAreFromSameRestaurantAndUserHasNoProcessingOrder() {
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").createRestaurant();
        Dish dish1 = new DishBuilder().setRestaurant(restaurant).createDish();
        Dish dish2 = new DishBuilder().setRestaurant(restaurant).createDish();
        OrderDish orderDish1 = new OrderDishBuilder().setDish(dish1).createOrderDish();
        OrderDish orderDish2 = new OrderDishBuilder().setDish(dish2).createOrderDish();
        Order order = new OrderBuilder()
                .setRestaurant(restaurant)
                .setOrderDishes(List.of(orderDish1, orderDish2))
                .createOrder();
        User client = new UserBuilder().setDocumentId(order.getClientId()).createUser();

        when(restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit())).thenReturn(restaurant);
        when(dishServicePort.findDishById(dish1.getId())).thenReturn(dish1);
        when(dishServicePort.findDishById(dish2.getId())).thenReturn(dish2);
        when(orderPersistencePort.findAllOrdersByClientId(client)).thenReturn(new ArrayList<>());

        orderUseCase.saveOrder(order);

        verify(orderPersistencePort, times(1)).saveOrder(order);
    }

    @Test
    @DisplayName("Should not save order when a dish is not from the same restaurant")
    void shouldNotSaveOrderWhenDishIsNotFromSameRestaurant() {
        Restaurant restaurant1 = new RestaurantBuilder().setNit("restaurant1Id").createRestaurant();
        Restaurant restaurant2 = new RestaurantBuilder().setNit("restaurant2Id").createRestaurant();
        Dish dish1 = new DishBuilder().setRestaurant(restaurant1).createDish();
        Dish dish2 = new DishBuilder().setRestaurant(restaurant2).createDish();
        OrderDish orderDish1 = new OrderDishBuilder().setDish(dish1).createOrderDish();
        OrderDish orderDish2 = new OrderDishBuilder().setDish(dish2).createOrderDish();
        Order order = new OrderBuilder()
                .setRestaurant(restaurant1)
                .setOrderDishes(List.of(orderDish1, orderDish2))
                .createOrder();

        when(restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit())).thenReturn(restaurant1);
        when(dishServicePort.findDishById(dish1.getId())).thenReturn(dish1);
        when(dishServicePort.findDishById(dish2.getId())).thenReturn(dish2);

        assertThrows(DishIsNotFromRestaurantException.class, () -> orderUseCase.saveOrder(order));
        verify(orderPersistencePort, times(0)).saveOrder(order);
    }

}
