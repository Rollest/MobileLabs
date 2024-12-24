package ru.mirea.zherebtsov.navigationdrawerapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zherebtsov.navigationdrawerapp.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Устанавливаем заголовок
        final TextView titleView = binding.textTitle;
        galleryViewModel.getTitle().observe(getViewLifecycleOwner(), titleView::setText);

        // Устанавливаем текст
        final TextView textView = binding.textHome;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Устанавливаем изображение
        final ImageView imageView = binding.imageHome;
        galleryViewModel.getImageResId().observe(getViewLifecycleOwner(), imageView::setImageResource);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}