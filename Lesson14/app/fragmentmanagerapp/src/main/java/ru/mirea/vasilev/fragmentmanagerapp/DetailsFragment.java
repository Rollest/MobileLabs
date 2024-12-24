package ru.mirea.vasilev.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DetailsFragment extends Fragment {

    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView textView = view.findViewById(R.id.country_details);

        // Получение ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Наблюдение за изменением данных
        viewModel.getSelectedCountry().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String country) {
                textView.setText("Вы выбрали: " + country);
            }
        });

        return view;
    }
}
