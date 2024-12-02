package ru.mirea.vasilevmn.shopproject.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;
import ru.mirea.vasilevmn.shopproject.domain.repository.ProductDao;

public class ProductRepositoryImpl {
    private final ProductDao productDao;

    public ProductRepositoryImpl(Context context) {

        AppDatabase db = AppDatabase.getInstance(context);
        this.productDao = db.productDao();
    }

    public List<ProductEntity> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<ProductEntity> getProductsByIds(List<Integer> ids) {
        List<ProductEntity> products = new ArrayList<>();
        for (int id : ids) {
            ProductEntity product = productDao.getProductById(id);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }

    public ProductEntity getProductById(int id){
        return productDao.getProductById(id);
    }

    public void addNewProduct(String title, String description, float price, String imageURL) {
        ProductEntity product = new ProductEntity(title, description, price, imageURL);
        productDao.insertProduct(product);
    }
}
