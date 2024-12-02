package ru.mirea.vasilevmn.shopproject.domain.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlist")
public class WishlistEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userLogin;
    public int productId;

    public WishlistEntity(String userLogin, int productId) {
        this.userLogin = userLogin;
        this.productId = productId;
    }
}
