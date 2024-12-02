package ru.mirea.vasilevmn.shopproject.domain.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public float price;
    public String imageURL;

    public ProductEntity(String title, String description, float price, String imageURL) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }
}
