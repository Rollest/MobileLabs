package ru.mirea.zherebtsov.navigationdrawerapp.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zherebtsov.navigationdrawerapp.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Устанавливаем заголовок
        final TextView titleView = binding.textTitle;
        slideshowViewModel.getTitle().observe(getViewLifecycleOwner(), titleView::setText);

        // Устанавливаем текст
        final TextView textView = binding.textHome;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Устанавливаем изображение
        final ImageView imageView = binding.imageHome;
        slideshowViewModel.getImageResId().observe(getViewLifecycleOwner(), imageView::setImageResource);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}