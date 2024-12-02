package ru.mirea.vasilevmn.shopproject.domain.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    List<ProductEntity> getAllProducts();

    @Query("SELECT * FROM products WHERE id = :id")
    ProductEntity getProductById(int id);

    @Insert
    void insertProduct(ProductEntity product);
}
