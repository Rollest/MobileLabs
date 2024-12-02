package ru.mirea.vasilevmn.shopproject.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.vasilevmn.shopproject.R;
import ru.mirea.vasilevmn.shopproject.databinding.ActivityMainBinding;
import ru.mirea.vasilevmn.shopproject.presentation.fragment.CurrencySelectionFragment;
import ru.mirea.vasilevmn.shopproject.presentation.fragment.ProductListFragment;
import ru.mirea.vasilevmn.shopproject.presentation.fragment.WishlistFragment;
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

        binding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);

        if (savedInstanceState == null) {
            openFragment(new ProductListFragment());
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
            }
        });
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_product_list) {
            fragment = new ProductListFragment();
        } else if (id == R.id.nav_wishlist) {
            fragment = new WishlistFragment();
        } else if (id == R.id.nav_login) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (id == R.id.nav_logout) {
            viewModel.logout();
            finish();
            return true;
        } else if (id == R.id.nav_currency_selection) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CurrencySelectionFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        if (fragment != null) {
            openFragment(fragment);
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateMenuItems(boolean isUserLoggedIn) {
        Menu menu = binding.bottomNavigation.getMenu();
        menu.findItem(R.id.nav_login).setVisible(!isUserLoggedIn);
        menu.findItem(R.id.nav_logout).setVisible(isUserLoggedIn);
    }
}
