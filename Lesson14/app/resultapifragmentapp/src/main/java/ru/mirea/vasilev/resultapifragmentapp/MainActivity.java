package ru.mirea.vasilev.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Загрузка фрагмента DataFragment при первом запуске
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new DataFragment(), "DataFragment")
                    .commit();
        }

        // Слушатель обработки результата из BottomSheetFragment
        getSupportFragmentManager().setFragmentResultListener(
                "requestKey",
                this,
                (requestKey, bundle) -> {
                    String result = bundle.getString("key");
                    Log.d("MainActivity", "Результат из BottomSheetFragment: " + result);
                }
        );
    }
}