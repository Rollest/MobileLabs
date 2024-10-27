package ru.mirea.vasilevmn.shopproject.data.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.Product;
import ru.mirea.vasilevmn.shopproject.domain.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> Products = new ArrayList<> (
            Arrays.asList(
                    new Product(1, "iPhone 16", "iPhone 16 оснастили новым процессором A18.", 110_000.99f),
                new Product(2, "Dyson dust sucker", "Sucks nice", 69999.99f),
                new Product(3, "Блендер", "Просто блендер", 7000f)
            )
    );

    @Override
    public List<Product> getAllProducts() {
        return Products;
    }

    @Override
    public Product getProductById(int id) {
        for (Product Product : Products) {
            if (Product.getId() == id) {
                return Product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProductsByTitle(String title) {
        List<Product> result = new ArrayList<>();
        for (Product Product : Products) {
            if (Product.getTitle().equalsIgnoreCase(title)) {
                result.add(Product);
            }
        }
        return result;
    }

    @Override
    public boolean addNewProduct(String title, String author, float price) {
        Product newProduct = new Product(this.Products.size() + 1, title, author, price);
        this.Products.add(newProduct);
        return true;
    }
}
