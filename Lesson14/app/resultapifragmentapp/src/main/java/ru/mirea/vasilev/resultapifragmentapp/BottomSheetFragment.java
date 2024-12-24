package ru.mirea.vasilev.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public	class	BottomSheetFragment	extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        TextView textViewResult = view.findViewById(R.id.textViewResult);

        // Получение переданных данных из DataFragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            String info = arguments.getString("info");
            textViewResult.setText(info);

            // Возврат данных в MainActivity
            Bundle resultBundle = new Bundle();
            resultBundle.putString("key", info);

            getParentFragmentManager().setFragmentResult("requestKey", resultBundle);
        }

        return view;
    }
}
