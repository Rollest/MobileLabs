package ru.mirea.vasilevmn.shopproject.domain.models;

import androidx.annotation.NonNull;

public class Product {

    private int id;
    private String title;
    private String description;
    private float price;
    public Product(int id, String title, String description, float price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                "}\n";
    }
}
