package ru.mirea.vasilevmn.shopproject.domain.usecases;

import ru.mirea.vasilevmn.shopproject.domain.repository.ProductRepository;

public class AddNewProductUseCase {
    private final ProductRepository productRepository;
    public AddNewProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(String title, String description, float price) {
        productRepository.addNewProduct(title, description, price);
    }
}
