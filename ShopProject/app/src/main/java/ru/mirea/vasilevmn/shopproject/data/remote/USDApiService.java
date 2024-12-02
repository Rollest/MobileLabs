package ru.mirea.vasilevmn.shopproject.data.remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface USDApiService {
    @GET("usd.json")
    Call<Map<String, CurrencyRatesResponse.Currency>> getCurrencyRates();
}
