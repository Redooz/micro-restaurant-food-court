package com.pragma.microservicefoodcourt.domain.model;

import java.time.LocalDate;

public class User {
    private LocalDate birthDate;
    private Role role;
    private String documentId;
    private String email;
    private String lastName;
    private String name;
    private String password;
    private String phone;

    public User(LocalDate birthDate, Role role, String documentId, String email, String lastName, String name, String password, String phone) {
        this.birthDate = birthDate;
        this.role = role;
        this.documentId = documentId;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
