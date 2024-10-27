package ru.mirea.vasilevmn.shopproject.data.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mirea.vasilevmn.shopproject.domain.models.User;
import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = Arrays.asList(
            new User(1, "Андрей Круглый", "andYRound@example.com", "password1", Arrays.asList(1, 2)),
            new User(2, "Леха Серый", "Akleksey@example.com", "password2", Arrays.asList(3)),
            new User(3, "Максим Грибов", "mussshrummm@example.com", "password3", Arrays.asList(2, 3))
    );
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<Integer> getUserWishedProducts(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user.getWishedProducts();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean signIn(String email, String password) {
        return true;
    }

    @Override
    public boolean signUp(String email, String password, String name) {
        return true;
    }

    @Override
    public boolean addProductToWished(int userId, int productId) {
        for (User user : users) {
            if (user.getId() == userId) {
                if (!user.getWishedProducts().contains(productId)) {
                    user.getWishedProducts().add(productId);
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
