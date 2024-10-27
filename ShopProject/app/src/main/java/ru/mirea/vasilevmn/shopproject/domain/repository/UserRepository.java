package ru.mirea.vasilevmn.shopproject.domain.repository;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.User;

public interface UserRepository {
    List<User> getAllUsers();
    List<Integer> getUserWishedProducts(int userId);
    boolean signIn(String email, String password);
    boolean signUp(String email, String password, String name);
    boolean addProductToWished(int userId, int productId);

}
