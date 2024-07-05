package com.pragma.microservicefoodcourt.domain.model;

public class Dish {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String urlImage;
    private String category;
    private String restaurantNIT;

    public Dish(Long id, String name, Double price, String description, String urlImage, String category, String restaurantNIT) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.restaurantNIT = restaurantNIT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurantNIT() {
        return restaurantNIT;
    }

    public void setRestaurantNIT(String restaurantNIT) {
        this.restaurantNIT = restaurantNIT;
    }

}
