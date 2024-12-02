package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyApiManager;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> userLoggedIn = new MutableLiveData<>();
    private final MutableLiveData<Boolean> currencyRatesUpdated = new MutableLiveData<>();
    private final FirebaseAuth auth;
    private final SharedPreferences sharedPreferences;
    private final CurrencyApiManager apiManager;

    public MainViewModel(Application application) {
        super(application);
        auth = FirebaseAuth.getInstance();
        sharedPreferences = application.getSharedPreferences("AppPrefs", Application.MODE_PRIVATE);
        apiManager = new CurrencyApiManager(application);

        userLoggedIn.setValue(auth.getCurrentUser() != null);
    }

    public LiveData<Boolean> isUserLoggedIn() {
        return userLoggedIn;
    }

    public void fetchCurrencyRates() {
        apiManager.fetchAndSaveCurrencyRates();
        currencyRatesUpdated.postValue(true);
    }

    public void logout() {
        auth.signOut();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user_email");
        editor.apply();
        userLoggedIn.postValue(false);
    }
}
