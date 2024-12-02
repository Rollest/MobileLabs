package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executors;

import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyApiManager;
import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyRepository;
import ru.mirea.vasilevmn.shopproject.data.repository.ProductRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;

public class ProductDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<ProductEntity> product = new MutableLiveData<>();
    private final MutableLiveData<String> convertedPrice = new MutableLiveData<>();
    private final ProductRepositoryImpl productRepository;
    private final CurrencyRepository currencyRepository;
    private final CurrencyApiManager currencyApiManager;

    public ProductDetailsViewModel(Application application) {
        super(application);
        productRepository = new ProductRepositoryImpl(application);
        currencyRepository = new CurrencyRepository(application);
        currencyApiManager = new CurrencyApiManager(application);
    }

    public LiveData<ProductEntity> getProduct() {
        return product;
    }

    public LiveData<String> getConvertedPrice() {
        return convertedPrice;
    }

    public void loadProductDetails(int productId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            ProductEntity productEntity = productRepository.getProductById(productId);
            product.postValue(productEntity);

            if (productEntity != null) {
                // Конвертируем цену
                String selectedCurrency = currencyRepository.getSelectedCurrency();
                float rate = currencyApiManager.getRate(selectedCurrency);
                float converted = productEntity.price * rate;
                String formattedPrice = String.format("%.2f %s", converted, selectedCurrency);
                convertedPrice.postValue(formattedPrice);
            }
        });
    }
}
