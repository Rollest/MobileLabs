package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import ru.mirea.vasilevmn.shopproject.data.repository.ProductRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;

public class ProductListViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ProductEntity>> productsLiveData = new MutableLiveData<>();
    private final ProductRepositoryImpl productRepository;

    public ProductListViewModel(Application application) {
        super(application);
        productRepository = new ProductRepositoryImpl(application.getApplicationContext());
        //productRepository.addNewProduct("Cat good", "akpsdkpas", 100, "https://cdn2.thecatapi.com/images/1.jpg");
        //productRepository.addNewProduct("Cat better", "ылвьазызхвоашхзыва", 123, "https://cdn2.thecatapi.com/images/2.jpg");
        //productRepository.addNewProduct("Kitty", "aosjdoasopdoas", 114, "https://cdn2.thecatapi.com/images/3.jpg");
        //productRepository.addNewProduct("Cato", "aosjdoasopdoas", 1312, "https://cdn2.thecatapi.com/images/11.jpg");
        //productRepository.addNewProduct("Catsie", "aosjdoasopdoas", 16, "https://cdn2.thecatapi.com/images/5.jpg");
        //productRepository.addNewProduct("KittyCat", "aosjdoasopdoas", 17, "https://cdn2.thecatapi.com/images/12.jpg");
    }

    public LiveData<List<ProductEntity>> getProductsLiveData() {
        return productsLiveData;
    }

    public void loadProducts() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<ProductEntity> products = productRepository.getAllProducts();
            productsLiveData.postValue(products);
        });
    }
}
