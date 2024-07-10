package com.pragma.microservicefoodcourt.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private List<OrderDish> orderDishes;
    private LocalDate date;
    private Long id;
    private OrderStatus status;
    private Restaurant restaurant;
    private String chefId;
    private String clientId;

    public Order(List<OrderDish> orderDishes, LocalDate date, Long id, OrderStatus status, Restaurant restaurant, String chefId, String clientId) {
        this.orderDishes = orderDishes;
        this.date = date;
        this.id = id;
        this.status = status;
        this.restaurant = restaurant;
        this.chefId = chefId;
        this.clientId = clientId;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
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

    public String getChefId() {
        return chefId;
    }

    public void setChefId(String chefId) {
        this.chefId = chefId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
