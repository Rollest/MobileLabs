package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import ru.mirea.vasilevmn.shopproject.data.repository.ProductRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.data.repository.WishlistRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;

public class WishlistViewModel extends AndroidViewModel {
    private final WishlistRepositoryImpl wishlistRepository;
    private final ProductRepositoryImpl productRepository;
    private final MutableLiveData<List<ProductEntity>> wishlistProducts = new MutableLiveData<>();

    public WishlistViewModel(@NonNull Application application) {
        super(application);
        wishlistRepository = new WishlistRepositoryImpl(application);
        productRepository = new ProductRepositoryImpl(application);
    }

    public LiveData<List<ProductEntity>> getWishlistProducts() {
        return wishlistProducts;
    }

    public void loadWishlistProducts() {
        wishlistRepository.getWishedProducts(productIds -> Executors.newSingleThreadExecutor().execute(() -> {
            List<ProductEntity> products = productRepository.getProductsByIds(productIds);
            wishlistProducts.postValue(products);
        }));
    }
}
