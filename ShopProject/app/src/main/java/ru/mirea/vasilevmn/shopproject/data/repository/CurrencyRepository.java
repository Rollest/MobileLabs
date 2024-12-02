package ru.mirea.vasilevmn.shopproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class CurrencyRepository {
    private static final String PREFS_NAME = "CurrencyPreferences";
    private static final String SELECTED_CURRENCY_KEY = "selectedCurrency";
    private final SharedPreferences preferences;

    public CurrencyRepository(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSelectedCurrency(String currencyCode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_CURRENCY_KEY, currencyCode.toUpperCase());
        editor.apply();
    }

    public String getSelectedCurrency() {
        return preferences.getString(SELECTED_CURRENCY_KEY, "USD");
    }
}
