package ru.mirea.vasilevmn.shopproject.domain.usecases;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.Product;
import ru.mirea.vasilevmn.shopproject.domain.repository.ProductRepository;

public class GetAllProductsUseCase {
    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> execute() {
        return productRepository.getAllProducts();
    }

}
