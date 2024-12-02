package ru.mirea.vasilevmn.shopproject.data.repository;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;
import ru.mirea.vasilevmn.shopproject.domain.models.WishlistEntity;
import ru.mirea.vasilevmn.shopproject.domain.repository.ProductDao;
import ru.mirea.vasilevmn.shopproject.domain.repository.WishlistDao;

@Database(entities = {ProductEntity.class, WishlistEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract WishlistDao wishlistDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        //context.deleteDatabase("shop_database");
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shop_database").build();
                }
            }
        }
        return INSTANCE;
    }
}