package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.Role;
import com.pragma.microservicefoodcourt.domain.model.User;

import java.time.LocalDate;

public class UserBuilder {
    private LocalDate birthDate;
    private Role role;
    private String documentId;
    private String email;
    private String lastName;
    private String name;
    private String password;
    private String phone;
    private User boss;

    public UserBuilder setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setBoss(User boss) {
        this.boss = boss;
        return this;
    }

    public User createUser() {
        return new User(birthDate, role, documentId, email, lastName, name, password, phone, boss);
    }
}