package ru.mirea.vasilevmn.shopproject.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import ru.mirea.vasilevmn.shopproject.databinding.FragmentProductDetailsBinding;
import ru.mirea.vasilevmn.shopproject.presentation.viewmodel.ProductDetailsViewModel;

public class ProductDetailsFragment extends Fragment {

    private FragmentProductDetailsBinding binding;
    private ProductDetailsViewModel viewModel;

    public static final String ARG_PRODUCT_ID = "product_id";
    private int productId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(ProductDetailsViewModel.class);

        if (getArguments() != null) {
            productId = getArguments().getInt(ARG_PRODUCT_ID);
        }

        observeViewModel();

        viewModel.loadProductDetails(productId);

        return binding.getRoot();
    }

    private void observeViewModel() {
        viewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                binding.productTitle.setText(product.title);
                binding.productDescription.setText(product.description);

                Picasso.get().load(product.imageURL).into(binding.productImage);
            }
        });

        viewModel.getConvertedPrice().observe(getViewLifecycleOwner(), price -> {
            if (price != null) {
                binding.productPrice.setText(price);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
