package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.OrderDish;

public class OrderDishBuilder {
    private Long id;
    private Dish dish;
    private int quantity;
    private Long orderId;

    public OrderDishBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderDishBuilder setDish(Dish dish) {
        this.dish = dish;
        return this;
    }

    public OrderDishBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderDishBuilder setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDish createOrderDish() {
        return new OrderDish(id, dish, quantity, orderId);
    }
}