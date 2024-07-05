package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    private String nit;

    private String address;

    private String name;

    private String ownerId;

    private String phone;

    private String urlLogo;
}
