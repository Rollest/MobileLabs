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

import ru.mirea.vasilevmn.shopproject.databinding.FragmentWishlistBinding;
import ru.mirea.vasilevmn.shopproject.presentation.adapter.WishlistAdapter;
import ru.mirea.vasilevmn.shopproject.presentation.viewmodel.WishlistViewModel;

public class WishlistFragment extends Fragment {
    private FragmentWishlistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);

        WishlistViewModel viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        binding.recyclerViewWishlist.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getWishlistProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null && !products.isEmpty()) {
                WishlistAdapter adapter = new WishlistAdapter(products);
                binding.recyclerViewWishlist.setAdapter(adapter);
            } else {
                binding.emptyMessage.setVisibility(View.VISIBLE);
            }
        });

        viewModel.loadWishlistProducts();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
