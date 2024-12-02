package ru.mirea.vasilevmn.shopproject.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.mirea.vasilevmn.shopproject.databinding.FragmentProductListBinding;
import ru.mirea.vasilevmn.shopproject.presentation.adapter.ProductAdapter;
import ru.mirea.vasilevmn.shopproject.presentation.viewmodel.ProductListViewModel;

public class ProductListFragment extends Fragment {

    private FragmentProductListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);

        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));

        ProductListViewModel viewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        viewModel.getProductsLiveData().observe(getViewLifecycleOwner(), products -> {
            if (products != null && !products.isEmpty()) {
                ProductAdapter adapter = new ProductAdapter(products);
                binding.recyclerViewProducts.setAdapter(adapter);
                binding.recyclerViewProducts.setVisibility(View.VISIBLE);
                binding.emptyMessage.setVisibility(View.GONE);
            } else {
                binding.recyclerViewProducts.setVisibility(View.GONE);
                binding.emptyMessage.setVisibility(View.VISIBLE);
            }
        });

        viewModel.loadProducts();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
