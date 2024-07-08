package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.Dish;

public class DishBuilder {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String urlImage;
    private Category category;
    private String restaurantNIT;

    public DishBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DishBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DishBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public DishBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public DishBuilder setUrlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public DishBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public DishBuilder setRestaurantNIT(String restaurantNIT) {
        this.restaurantNIT = restaurantNIT;
        return this;
    }

    public Dish createDish() {
        return new Dish(id, name, price, description, urlImage, category, restaurantNIT);
    }
}