package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.builder.UserBuilder;
import com.pragma.microservicefoodcourt.domain.constant.OrderConstant;
import com.pragma.microservicefoodcourt.domain.exception.DishIsNotFromRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.EmployeeDoesNotBelongToRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.UserHasProcessingOrderException;
import com.pragma.microservicefoodcourt.domain.model.*;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantServicePort restaurantServicePort, IDishServicePort dishServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
        this.dishServicePort = dishServicePort;
    }

    @Override
    public void saveOrder(Order order) {
        // Check if restaurant exists
        restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit());

        if (!allDishesAreFromSameRestaurant(order)) {
            throw new DishIsNotFromRestaurantException(
                    String.format(OrderConstant.DISH_IS_NOT_FROM_RESTAURANT, order.getRestaurant().getNit())
            );
        }

        if (userHasProcessingOrder(order.getClientId())) {
            throw new UserHasProcessingOrderException(
                    String.format(OrderConstant.USER_HAS_PROCESSING_ORDER, order.getClientId())
            );
        }

        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<Order> findAllOrdersByStatusAndRestaurant(User loggedEmployee, OrderStatus status, Restaurant restaurant, int page, int size) {
        if (!loggedEmployee.getBoss().getDocumentId().equals(restaurant.getOwnerId())) {
            throw new EmployeeDoesNotBelongToRestaurantException(
                    String.format(OrderConstant.EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT, loggedEmployee.getDocumentId())
            );
        }

        return orderPersistencePort.findAllOrdersByStatusAndRestaurant(status, restaurant, page, size);
    }

    private boolean userHasProcessingOrder(String id) {
        User user = new UserBuilder().setDocumentId(id).createUser();

        List<Order> orders = orderPersistencePort.findAllOrdersByClientId(user);
        return orders.stream()
                .anyMatch(order ->
                        order.getStatus() == OrderStatus.PENDING ||
                        order.getStatus() == OrderStatus.IN_PROGRESS ||
                        order.getStatus() == OrderStatus.READY
                );
    }

    private boolean allDishesAreFromSameRestaurant(Order order) {
        String restaurantId = order.getRestaurant().getNit();
        List<OrderDish> orderDishes = order.getOrderDishes();
        List<Dish> dishes = orderDishes.stream().map(OrderDish::getDish).toList();
        List<Dish> dishesFromDb = new ArrayList<>(List.of());

        for (Dish dish : dishes) {
            var dbDish = dishServicePort.findDishById(dish.getId());
            dishesFromDb.add(dbDish);
        }

        return dishesFromDb.stream().allMatch(dish -> dish.getRestaurant().getNit().equals(restaurantId));
    }
}
