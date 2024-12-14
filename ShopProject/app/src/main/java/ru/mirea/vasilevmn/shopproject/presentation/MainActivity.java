package ru.mirea.vasilevmn.shopproject.presentation;

import android.os.Bundle;
import android.view.Menu;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.vasilevmn.shopproject.R;
import ru.mirea.vasilevmn.shopproject.databinding.ActivityMainBinding;
import ru.mirea.vasilevmn.shopproject.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.isUserLoggedIn().observe(this, this::updateMenuItems);

        viewModel.fetchCurrencyRates();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_logout) {
                viewModel.logout();
                viewModel.isUserLoggedIn().observe(this, this::updateMenuItems);
                return true;
            } else if (id == R.id.nav_login) {
                navController.navigate(R.id.nav_login);
                return true;
            }
            return NavigationUI.onNavDestinationSelected(item, navController);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!navController.popBackStack()) {
                    finish();
                }
            }
        });
    }

    private void updateMenuItems(boolean isUserLoggedIn) {
        Menu menu = binding.bottomNavigation.getMenu();
        menu.findItem(R.id.nav_login).setVisible(!isUserLoggedIn);
        menu.findItem(R.id.nav_logout).setVisible(isUserLoggedIn);
    }
}
