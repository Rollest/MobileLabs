package ru.mirea.vasilevmn.shopproject.domain.usecases;

import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;

public class SignUpUseCase {
    private final UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String name, String email, String password) {
        return userRepository.signUp(name, email, password);
    }
}
