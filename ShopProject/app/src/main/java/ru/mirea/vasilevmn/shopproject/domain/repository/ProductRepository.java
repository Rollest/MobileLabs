package ru.mirea.vasilevmn.shopproject.domain.repository;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.Product;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> getProductsByTitle(String title);
    boolean addNewProduct(String title, String description, float price);
}
