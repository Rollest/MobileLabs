package ru.mirea.vasilevmn.shopproject.presentation.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.R;
import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyApiManager;
import ru.mirea.vasilevmn.shopproject.data.repository.CurrencyRepository;
import ru.mirea.vasilevmn.shopproject.domain.models.ProductEntity;
import ru.mirea.vasilevmn.shopproject.presentation.MainActivity;
import ru.mirea.vasilevmn.shopproject.presentation.fragment.ProductDetailsFragment;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private final List<ProductEntity> wishedProducts;

    public WishlistAdapter(List<ProductEntity> wishedProducts) {
        this.wishedProducts = wishedProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductEntity product = wishedProducts.get(position);
        holder.textViewTitle.setText(product.title);

        float priceInUSD = product.price;

        CurrencyRepository currencyRepository = new CurrencyRepository(holder.itemView.getContext());
        CurrencyApiManager currencyApiManager = new CurrencyApiManager(holder.itemView.getContext());
        String selectedCurrency = currencyRepository.getSelectedCurrency();
        float rate = currencyApiManager.getRate(selectedCurrency);

        float convertedPrice = priceInUSD * rate;
        holder.textViewPrice.setText(String.format("%.2f %s", convertedPrice, selectedCurrency));

        Picasso.get()
                .load(product.imageURL)
                .placeholder(R.drawable.placeholder_product)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ProductDetailsFragment.ARG_PRODUCT_ID, product.id);

            NavController navController = Navigation.findNavController(holder.itemView);
            navController.navigate(R.id.action_nav_wishlist_to_productDetailsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return wishedProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrice;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.productTitle);
            textViewPrice = itemView.findViewById(R.id.productPrice);
            imageView = itemView.findViewById(R.id.productImage);
        }
    }
}
