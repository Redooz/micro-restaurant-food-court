package com.pragma.microservicefoodcourt.domain.model;

public class Restaurant {
    private String address;
    private String name;
    private String nit;
    private String ownerId;
    private String phone;
    private String urlLogo;

    public Restaurant(String address, String name, String nit, String ownerId, String phone, String urlLogo) {
        this.address = address;
        this.name = name;
        this.nit = nit;
        this.ownerId = ownerId;
        this.phone = phone;
        this.urlLogo = urlLogo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
