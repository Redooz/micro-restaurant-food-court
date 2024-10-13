package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;

public class RestaurantBuilder {
    private String address;
    private String name;
    private String nit;
    private String ownerId;
    private String phone;
    private String urlLogo;

    public RestaurantBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public RestaurantBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantBuilder setNit(String nit) {
        this.nit = nit;
        return this;
    }

    public RestaurantBuilder setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public RestaurantBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RestaurantBuilder setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
        return this;
    }

    public Restaurant createRestaurant() {
        return new Restaurant(address, name, nit, ownerId, phone, urlLogo);
    }
}