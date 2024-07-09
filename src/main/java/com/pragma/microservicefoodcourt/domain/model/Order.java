package com.pragma.microservicefoodcourt.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private List<DishItem> dishItems;
    private LocalDate date;
    private Long id;
    private OrderStatus status;
    private Restaurant restaurant;
    private User chef;
    private User client;

    public Order(List<DishItem> dishItems, LocalDate date, Long id, OrderStatus status, Restaurant restaurant, User chef, User client) {
        this.dishItems = dishItems;
        this.date = date;
        this.id = id;
        this.status = status;
        this.restaurant = restaurant;
        this.chef = chef;
        this.client = client;
    }

    public List<DishItem> getDishItems() {
        return dishItems;
    }

    public void setDishItems(List<DishItem> dishItems) {
        this.dishItems = dishItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
