package com.pragma.microservicefoodcourt.domain.model;

public class OrderDish {
    private Long id;
    private Dish dish;
    private int quantity;
    private Long orderId;

    public OrderDish(Long id, Dish dish, int quantity, Long orderId) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
