package ru.mirea.vasilevmn.shopproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import ru.mirea.vasilevmn.shopproject.domain.models.WishlistEntity;
import ru.mirea.vasilevmn.shopproject.domain.repository.Callback;
import ru.mirea.vasilevmn.shopproject.domain.repository.WishlistDao;

public class WishlistRepositoryImpl {
    private final WishlistDao wishlistDao;
    private final SharedPreferences sharedPreferences;


    public WishlistRepositoryImpl(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.wishlistDao = db.wishlistDao();
        this.sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
    }

    public void getWishedProducts(Callback<List<Integer>> callback) {
        String userEmail = sharedPreferences.getString("user_email", null);
        if (userEmail != null) {
            // Если пользователь авторизован, запрашиваем из базы данных
            getUserWishedProducts(userEmail, callback);
        } else {
            // Если пользователь гость, возвращаем данные из SharedPreferences
            Executors.newSingleThreadExecutor().execute(() -> {
                List<Integer> guestWishedProducts = getGuestWishedProducts();
                new android.os.Handler(Looper.getMainLooper()).post(() -> {
                    callback.onResult(guestWishedProducts);
                });
            });
        }
    }

    public boolean addProductToWished(int productId) {
        String userEmail = sharedPreferences.getString("user_email", null);
        if (userEmail != null) {
            // Пользователь авторизован, добавляем в базу данных
            addUserProductToWishlist(userEmail, productId);
            return true;
        } else {
            // Пользователь гость, добавляем в SharedPreferences
            return addGuestProductToWished(productId);
        }
    }

    public List<Integer> getGuestWishedProducts() {
        Set<String> savedSet = sharedPreferences.getStringSet("guest_wishlist", new HashSet<>());
        List<Integer> guestWishedProducts = new ArrayList<>();
        for (String productId : savedSet) {
            guestWishedProducts.add(Integer.parseInt(productId));
        }
        return guestWishedProducts;
    }

    public boolean addGuestProductToWished(int productId) {
        Set<String> savedSet = sharedPreferences.getStringSet("guest_wishlist", new HashSet<>());
        Set<String> updatedSet = new HashSet<>(savedSet);

        if (!updatedSet.contains(String.valueOf(productId))) {
            updatedSet.add(String.valueOf(productId));
            sharedPreferences.edit().putStringSet("guest_wishlist", updatedSet).apply();
            return true;
        }
        return false;
    }

    public void getUserWishedProducts(String userLogin, Callback<List<Integer>> callback) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Integer> wishedProducts = wishlistDao.getWishedProducts(userLogin);
            new android.os.Handler(Looper.getMainLooper()).post(() -> {
                callback.onResult(wishedProducts);
            });
        });
    }

    public void addUserProductToWishlist(String userLogin, int productId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (!wishlistDao.getWishedProducts(userLogin).contains(productId)) {
                wishlistDao.insertWishlistItem(new WishlistEntity(userLogin, productId));
            }
        });
    }
}