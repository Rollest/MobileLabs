package ru.mirea.zherebtsov.bottomnavigationapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zherebtsov.bottomnavigationapp.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Устанавливаем заголовок
        final TextView titleView = binding.textTitle;
        notificationsViewModel.getTitle().observe(getViewLifecycleOwner(), titleView::setText);

        // Устанавливаем текст
        final TextView textView = binding.textHome;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Устанавливаем изображение
        final ImageView imageView = binding.imageHome;
        notificationsViewModel.getImageResId().observe(getViewLifecycleOwner(), imageView::setImageResource);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}