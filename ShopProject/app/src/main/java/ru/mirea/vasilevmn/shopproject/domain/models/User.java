package ru.mirea.vasilevmn.shopproject.domain.models;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Integer> wishedProducts;

    public User(int id, String name, String email, String password, List<Integer> wishedProducts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wishedProducts = wishedProducts;
    }

    public int getId() {
        return this.id;
    }

    public List<Integer> getWishedProducts() {
        return this.wishedProducts;
    }
}
