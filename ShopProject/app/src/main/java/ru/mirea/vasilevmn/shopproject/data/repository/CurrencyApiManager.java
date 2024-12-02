package ru.mirea.vasilevmn.shopproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.vasilevmn.shopproject.data.remote.USDApiService;
import ru.mirea.vasilevmn.shopproject.data.remote.CurrencyRatesResponse;
import ru.mirea.vasilevmn.shopproject.data.remote.USDApiService;

import java.util.Map;

public class CurrencyApiManager {
    private static final String BASE_URL = "https://www.floatrates.com/daily/";
    private static final String PREFS_NAME = "CurrencyRates";
    private final USDApiService apiService;
    private final SharedPreferences preferences;

    public CurrencyApiManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(USDApiService.class);
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void fetchAndSaveCurrencyRates() {
        apiService.getCurrencyRates().enqueue(new retrofit2.Callback<Map<String, CurrencyRatesResponse.Currency>>() {
            @Override
            public void onResponse(Call<Map<String, CurrencyRatesResponse.Currency>> call,
                                   retrofit2.Response<Map<String, CurrencyRatesResponse.Currency>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveRates(response.body());
                }
            }

            @Override
            public void onFailure(Call<Map<String, CurrencyRatesResponse.Currency>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void saveRates(Map<String, CurrencyRatesResponse.Currency> rates) {
        SharedPreferences.Editor editor = preferences.edit();
        for (String key : rates.keySet()) {
            CurrencyRatesResponse.Currency currency = rates.get(key);
            editor.putFloat(currency.code, (float) currency.rate);
        }
        editor.putFloat("USD", 1.0f);
        editor.apply();
    }

    public float getRate(String currencyCode) {
        return preferences.getFloat(currencyCode.toUpperCase(), 1.0f);
    }
}
