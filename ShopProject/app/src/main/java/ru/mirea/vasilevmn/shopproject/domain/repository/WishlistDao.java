package ru.mirea.vasilevmn.shopproject.domain.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.WishlistEntity;

@Dao
public interface WishlistDao {
    @Query("SELECT productId FROM wishlist WHERE userLogin = :userLogin")
    List<Integer> getWishedProducts(String userLogin);

    @Insert
    void insertWishlistItem(WishlistEntity wishlistEntity);
}