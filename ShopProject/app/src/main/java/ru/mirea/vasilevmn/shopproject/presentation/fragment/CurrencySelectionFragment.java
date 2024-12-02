package ru.mirea.vasilevmn.shopproject.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import ru.mirea.vasilevmn.shopproject.R;
import ru.mirea.vasilevmn.shopproject.presentation.viewmodel.CurrencySelectionViewModel;

public class CurrencySelectionFragment extends Fragment {

    private CurrencySelectionViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_selection, container, false);

        viewModel = new ViewModelProvider(this).get(CurrencySelectionViewModel.class);

        Spinner currencySpinner = view.findViewById(R.id.currencySpinner);

        viewModel.getAvailableCurrencies().observe(getViewLifecycleOwner(), currencies -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    currencies
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            currencySpinner.setAdapter(adapter);
        });

        viewModel.getSelectedCurrency().observe(getViewLifecycleOwner(), selectedCurrency -> {
            if (selectedCurrency != null) {
                int position = ((ArrayAdapter<String>) currencySpinner.getAdapter()).getPosition(selectedCurrency);
                if (position >= 0) {
                    currencySpinner.setSelection(position);
                }
            }
        });

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCurrency = parent.getItemAtPosition(position).toString();
                viewModel.selectCurrency(selectedCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }
}
