package ru.mirea.vasilevmn.shopproject.domain.usecases;

import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;

public class AddProductToWishedUseCase {
    private final UserRepository userRepository;

    public AddProductToWishedUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(int userId, int productId) {
        return userRepository.addProductToWished(userId, productId);
    }
}
