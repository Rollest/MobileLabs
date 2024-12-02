package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import ru.mirea.vasilevmn.shopproject.R;
import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyRepository;

public class CurrencySelectionViewModel extends AndroidViewModel {

    private final CurrencyRepository currencyRepository;
    private final MutableLiveData<List<String>> availableCurrencies = new MutableLiveData<>();
    private final MutableLiveData<String> selectedCurrency = new MutableLiveData<>();

    public CurrencySelectionViewModel(@NonNull Application application) {
        super(application);
        currencyRepository = new CurrencyRepository(application);

        loadAvailableCurrencies();

        selectedCurrency.setValue(currencyRepository.getSelectedCurrency());
    }

    public LiveData<List<String>> getAvailableCurrencies() {
        return availableCurrencies;
    }

    public LiveData<String> getSelectedCurrency() {
        return selectedCurrency;
    }

    public void selectCurrency(String currency) {
        selectedCurrency.setValue(currency);
        currencyRepository.saveSelectedCurrency(currency);
    }

    private void loadAvailableCurrencies() {
        List<String> currencies = Arrays.asList(getApplication().getResources().getStringArray(R.array.currencies));
        availableCurrencies.setValue(currencies);
    }
}
