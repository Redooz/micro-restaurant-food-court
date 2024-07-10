package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.*;

import java.time.LocalDate;
import java.util.List;

public class OrderBuilder {
    private List<OrderDish> orderDishes;
    private LocalDate date;
    private Long id;
    private OrderStatus status;
    private Restaurant restaurant;
    private String chef;
    private String client;

    public OrderBuilder setDishItems(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
        return this;
    }

    public OrderBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public OrderBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public OrderBuilder setChef(String chef) {
        this.chef = chef;
        return this;
    }

    public OrderBuilder setClient(String client) {
        this.client = client;
        return this;
    }

    public Order createOrder() {
        return new Order(orderDishes, date, id, status, restaurant, chef, client);
    }
}