package ru.mirea.vasilev.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CountryListFragment extends Fragment {

    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        // Список стран
        ListView listView = view.findViewById(R.id.country_list);
        String[] countries = {"Россия", "США", "Китай", "Германия", "Италия"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        // Получение ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Обработка клика по пунктам списка
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countries[position];
                viewModel.selectCountry(selectedCountry); // Передача данных в ViewModel

                // Замена фрагмента с детальными данными
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.details_container, new DetailsFragment())
                        .commit();
            }
        });

        return view;
    }
}
