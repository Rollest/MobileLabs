package ru.mirea.vasilevmn.shopproject.domain.usecases;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;

public class GetUserWishedProductsUseCase {
    private final UserRepository userRepository;

    public GetUserWishedProductsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Integer> execute(int userId) {
        return userRepository.getUserWishedProducts(userId);
    }
}
