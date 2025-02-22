package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.spi.IVerificationApiPort;
import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.builder.TraceabilityBuilder;
import com.pragma.microservicefoodcourt.domain.builder.UserBuilder;
import com.pragma.microservicefoodcourt.domain.constant.OrderConstant;
import com.pragma.microservicefoodcourt.domain.exception.*;
import com.pragma.microservicefoodcourt.domain.model.*;
import com.pragma.microservicefoodcourt.domain.model.enums.NotificationMethod;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.ITraceabilityApiPort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;
    private final IUserApiPort userApiPort;
    private final IVerificationApiPort verificationApiPort;
    private final ITraceabilityApiPort traceabilityApiPort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantServicePort restaurantServicePort, IDishServicePort dishServicePort, IUserApiPort userApiPort, IVerificationApiPort verificationApiPort, ITraceabilityApiPort traceabilityApiPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
        this.dishServicePort = dishServicePort;
        this.userApiPort = userApiPort;
        this.verificationApiPort = verificationApiPort;
        this.traceabilityApiPort = traceabilityApiPort;
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
        Order savedOrder = orderPersistencePort.saveOrder(order);

        User client = userApiPort.findUserById(savedOrder.getClientId());

        Traceability traceability = new TraceabilityBuilder()
                .setOrderId(savedOrder.getId())
                .setClientId(client.getDocumentId())
                .setClientEmail(client.getEmail())
                .setStartTime(LocalDateTime.now())
                .setNewStatus(savedOrder.getStatus())
                .setRestaurantNit(savedOrder.getRestaurant().getNit())
                .createTraceability();

        traceabilityApiPort.saveTraceability(traceability);
    }

    @Override
    public List<Order> findAllOrdersByStatusAndRestaurant(User loggedEmployee, OrderStatus status, Restaurant restaurant, int page, int size) {
        Restaurant foundRestaurant = restaurantServicePort.findRestaurantByNit(restaurant.getNit());
        User foundEmployee = userApiPort.findUserById(loggedEmployee.getDocumentId());

        if (!foundEmployee.getBoss().getDocumentId().equals(foundRestaurant.getOwnerId())) {
            throw new EmployeeDoesNotBelongToRestaurantException(
                    String.format(OrderConstant.EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT, foundEmployee.getDocumentId())
            );
        }

        List<Order> orders = orderPersistencePort.findAllOrdersByStatusAndRestaurant(status, restaurant, page, size);

        if (orders.isEmpty()) {
            throw new NoDataFoundException(OrderConstant.ORDER_EMPTY_LIST);
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderPersistencePort.findOrderById(orderId).orElseThrow(
                () -> new NoDataFoundException(
                        String.format(OrderConstant.ORDER_NOT_FOUND, orderId)
                )
        );
    }

    @Override
    public void assignOrderToEmployee(User loggedEmployee, Long orderId) {
        Order order = orderValidationForEmployee(loggedEmployee, orderId);

        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setChefId(loggedEmployee.getDocumentId());

        Traceability traceability = traceabilityApiPort.findTraceabilityByOrderId(orderId);

        traceability.setEmployeeId(loggedEmployee.getDocumentId());
        traceability.setEmployeeEmail(loggedEmployee.getEmail());
        traceability.setLastStatus(traceability.getNewStatus());
        traceability.setNewStatus(order.getStatus());

        traceabilityApiPort.updateTraceability(orderId, traceability);
        orderPersistencePort.updateOrder(order);
    }

    @Override
    public void finishOrder(User loggedEmployee, Long orderId) {
        Order order = orderValidationForEmployee(loggedEmployee, orderId);
        User client = userApiPort.findUserById(order.getClientId());

        verificationApiPort.sendCode(client.getPhone(), NotificationMethod.SMS);

        order.setStatus(OrderStatus.READY);

        Traceability traceability = traceabilityApiPort.findTraceabilityByOrderId(orderId);

        traceability.setLastStatus(traceability.getNewStatus());
        traceability.setNewStatus(order.getStatus());
        traceability.setEndTime(LocalDateTime.now());

        traceabilityApiPort.updateTraceability(orderId, traceability);
        orderPersistencePort.updateOrder(order);
    }

    @Override
    public void deliverOrder(User loggedEmployee, Long orderId, String code) {
        Order order = orderValidationForEmployee(loggedEmployee, orderId);
        User client = userApiPort.findUserById(order.getClientId());

        if (order.getStatus() != OrderStatus.READY) {
            throw new OrderStatusException(
                    String.format(OrderConstant.CANT_CHANGE_ORDER_STATUS, order.getStatus(), OrderStatus.READY)
            );
        }

        verificationApiPort.verifyCode(client.getPhone(), code);

        order.setStatus(OrderStatus.DELIVERED);

        Traceability traceability = traceabilityApiPort.findTraceabilityByOrderId(orderId);

        traceability.setLastStatus(traceability.getNewStatus());
        traceability.setNewStatus(order.getStatus());

        traceabilityApiPort.updateTraceability(orderId, traceability);
        orderPersistencePort.updateOrder(order);
    }

    @Override
    public void cancelOrder(User loggedCustomer, Long orderId) {
        Order order = orderValidationForCustomer(loggedCustomer, orderId);

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderStatusException(
                    String.format(OrderConstant.CANT_CHANGE_ORDER_STATUS, order.getStatus(), OrderStatus.DELIVERED)
            );
        }

        order.setStatus(OrderStatus.CANCELLED);

        Traceability traceability = traceabilityApiPort.findTraceabilityByOrderId(orderId);

        traceability.setLastStatus(traceability.getNewStatus());
        traceability.setNewStatus(order.getStatus());

        traceabilityApiPort.updateTraceability(orderId, traceability);
        orderPersistencePort.updateOrder(order);
    }

    private Order orderValidationForEmployee(User loggedEmployee, Long orderId) {
        Order order = this.findOrderById(orderId);
        Restaurant foundRestaurant = restaurantServicePort.findRestaurantByNit(order.getRestaurant().getNit());
        User foundEmployee = userApiPort.findUserById(loggedEmployee.getDocumentId());

        if (!foundEmployee.getBoss().getDocumentId().equals(foundRestaurant.getOwnerId())) {
            throw new EmployeeDoesNotBelongToRestaurantException(
                    String.format(OrderConstant.EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT, foundEmployee.getDocumentId())
            );
        }

        return order;
    }

    private Order orderValidationForCustomer(User loggedCustomer, Long orderId) {
        Order order = this.findOrderById(orderId);
        User foundCustomer = userApiPort.findUserById(loggedCustomer.getDocumentId());

        if (!foundCustomer.getDocumentId().equals(order.getClientId())) {
            throw new UserDoesNotOwnOrderException(
                    String.format(OrderConstant.USER_DOES_NOT_OWN_ORDER, foundCustomer.getDocumentId(), order.getId())
            );
        }

        return order;
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
