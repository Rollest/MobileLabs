package ru.mirea.vasilevmn.shopproject.domain.usecases;

import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;

public class SignInUseCase {
    private final UserRepository userRepository;

    public SignInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String email, String password) {
        return userRepository.signIn(email, password);
    }

}
