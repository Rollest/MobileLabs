package ru.mirea.vasilev.resultapifragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DataFragment extends Fragment {
    private EditText editTextInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        editTextInfo = view.findViewById(R.id.editTextInfo);
        Button buttonOpenBottomSheet = view.findViewById(R.id.buttonOpenBottomSheet);

        buttonOpenBottomSheet.setOnClickListener(v -> {
            // Получение текста из EditText
            String info = editTextInfo.getText().toString();

            // Передача данных в BottomSheetFragment
            Bundle bundle = new Bundle();
            bundle.putString("info", info);

            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.setArguments(bundle);

            bottomSheetFragment.show(getParentFragmentManager(), "BottomSheetFragment");
        });

        return view;
    }
}
