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
import com.pragma.microservicefoodcourt.domain.exception.EmployeeDoesNotBelongToRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.model.*;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private IUserApiPort userApiPort;

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

    @Test
    @DisplayName("Should return orders when employee belongs to restaurant")
    void shouldReturnOrdersWhenEmployeeBelongsToRestaurant() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(owner).createUser();
        Order order1 = new OrderBuilder().setRestaurant(restaurant).setStatus(OrderStatus.PENDING).createOrder();
        Order order2 = new OrderBuilder().setRestaurant(restaurant).setStatus(OrderStatus.PENDING).createOrder();
        List<Order> orders = List.of(order1, order2);

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);
        when(orderPersistencePort.findAllOrdersByStatusAndRestaurant(order1.getStatus(), restaurant, 0, 10)).thenReturn(orders);

        List<Order> result = orderUseCase.findAllOrdersByStatusAndRestaurant(employee, order1.getStatus(), restaurant, 0, 10);

        assertEquals(orders, result);
        verify(orderPersistencePort, times(1)).findAllOrdersByStatusAndRestaurant(order1.getStatus(), restaurant, 0, 10);
    }

    @Test
    @DisplayName("Should not return orders when employee does not belong to restaurant")
    void shouldNotReturnOrdersWhenEmployeeDoesNotBelongToRestaurant() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        User notOwner = new UserBuilder().setDocumentId("notOwnerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(notOwner).createUser();
        OrderStatus status = OrderStatus.PENDING;

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);

        assertThrows(
                EmployeeDoesNotBelongToRestaurantException.class,
                () -> orderUseCase.findAllOrdersByStatusAndRestaurant(employee, status , restaurant, 0, 10)
        );
        verify(orderPersistencePort, times(0)).findAllOrdersByStatusAndRestaurant(status, restaurant, 0, 10);
    }

    @Test
    @DisplayName("Should not return orders when there are no orders")
    void shouldNotReturnOrdersWhenThereAreNoOrders() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(owner).createUser();
        OrderStatus status = OrderStatus.PENDING;

        when(restaurantServicePort.findRestaurantByNit(restaurant.getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);
        when(orderPersistencePort.findAllOrdersByStatusAndRestaurant(status, restaurant, 0, 10)).thenReturn(new ArrayList<>());

        assertThrows(
                NoDataFoundException.class,
                () -> orderUseCase.findAllOrdersByStatusAndRestaurant(employee, status, restaurant, 0, 10)
        );
        verify(orderPersistencePort, times(1)).findAllOrdersByStatusAndRestaurant(status, restaurant, 0, 10);
    }

    @Test
    @DisplayName("Should assign order to employee when employee belongs to restaurant")
    void shouldAssignOrderToEmployeeWhenEmployeeBelongsToRestaurant() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(owner).createUser();
        Order order = new OrderBuilder().setRestaurant(restaurant).setStatus(OrderStatus.PENDING).createOrder();

        when(orderPersistencePort.findOrderById(order.getId())).thenReturn(java.util.Optional.of(order));
        when(restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);

        orderUseCase.assignOrderToEmployee(employee, order.getId());

        verify(orderPersistencePort, times(1)).updateOrder(order);
    }

    @Test
    @DisplayName("Should not assign order to employee when employee does not belong to restaurant")
    void shouldNotAssignOrderToEmployeeWhenEmployeeDoesNotBelongToRestaurant() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        User notOwner = new UserBuilder().setDocumentId("notOwnerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(notOwner).createUser();
        Order order = new OrderBuilder().setId(1L).setRestaurant(restaurant).setStatus(OrderStatus.PENDING).createOrder();

        when(orderPersistencePort.findOrderById(order.getId())).thenReturn(Optional.of(order));
        when(restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);

        Long orderId = order.getId();
        assertThrows(
                EmployeeDoesNotBelongToRestaurantException.class,
                () -> orderUseCase.assignOrderToEmployee(employee, orderId)
        );
        verify(orderPersistencePort, times(0)).updateOrder(order);
    }

    @Test
    @DisplayName("Should not assign order to employee when order does not exist")
    void shouldNotAssignOrderToEmployeeWhenOrderDoesNotExist() {
        User owner = new UserBuilder().setDocumentId("ownerId").createUser();
        Restaurant restaurant = new RestaurantBuilder().setNit("restaurantId").setOwnerId(owner.getDocumentId()).createRestaurant();
        User employee = new UserBuilder().setDocumentId("employeeId").createUser();
        User foundEmployee = new UserBuilder().setDocumentId(employee.getDocumentId()).setBoss(owner).createUser();
        Order order = new OrderBuilder().setId(1L).setRestaurant(restaurant).setStatus(OrderStatus.PENDING).createOrder();

        when(orderPersistencePort.findOrderById(order.getId())).thenReturn(Optional.empty());
        when(restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit())).thenReturn(restaurant);
        when(userApiPort.findUserById(employee.getDocumentId())).thenReturn(foundEmployee);

        Long orderId = order.getId();
        assertThrows(
                NoDataFoundException.class,
                () -> orderUseCase.assignOrderToEmployee(employee, orderId)
        );

        verify(orderPersistencePort, times(0)).updateOrder(order);
        }

}
