package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.exception.DishIsNotFromRestaurantException;
import com.pragma.microservicefoodcourt.domain.exception.UserHasProcessingOrderException;
import com.pragma.microservicefoodcourt.domain.model.*;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantServicePort restaurantServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
    }

    @Override
    public void saveOrder(Order order) {
        // Check if restaurant exists
        restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit());

        if (!allDishesAreFromSameRestaurant(order)) {
            throw new DishIsNotFromRestaurantException(order.getRestaurant().getNit());
        }

        if (userHasProcessingOrder(order.getClient())) {
            throw new UserHasProcessingOrderException(order.getClient().getDocumentId());
        }

        order.setStatus(OrderStatus.PENDING);
        orderPersistencePort.saveOrder(order);
    }

    public boolean userHasProcessingOrder(User user) {
        List<Order> orders = orderPersistencePort.findAllOrdersByClientId(user);
        return orders.stream()
                .anyMatch(order ->
                        order.getStatus() == OrderStatus.PENDING ||
                        order.getStatus() == OrderStatus.IN_PROGRESS ||
                        order.getStatus() == OrderStatus.READY
                );
    }

    public boolean allDishesAreFromSameRestaurant(Order order) {
        String restaurantId = order.getRestaurant().getNit();
        List<OrderDish> orderDishes = order.getDishItems();
        List<Dish> dishes = orderDishes.stream().map(OrderDish::getDish).toList();

        return dishes.stream().allMatch(dish -> dish.getRestaurant().getNit().equals(restaurantId));
    }
}
