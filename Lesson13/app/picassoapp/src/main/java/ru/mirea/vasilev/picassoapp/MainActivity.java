package ru.mirea.vasilev.picassoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
// RecyclerView
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ImageAdapter imageAdapter;
    private FloatingActionButton fabSettings;
    private boolean isGrid = true;
    private int numOfColumns = 2;
    private String clientId = "C-wUB-JcPBepxP_nm9InfB_aMAGFnpytTWyIxQeulXI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        fabSettings = findViewById(R.id.fab_settings);

        imageAdapter = new ImageAdapter(this, null);
        recyclerView.setAdapter(imageAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));

        fetchImages();

        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLayout();
            }
        });
    }

    private void fetchImages() {
        UnsplashApi apiService = ApiClient.getClient().create(UnsplashApi.class);
        Call<List<ImageItem>> call = apiService.getPhotos(clientId, 30);

        call.enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>> call, Response<List<ImageItem>> response) {
                if (response.isSuccessful()) {
                    List<ImageItem> imageList = response.body();
                    imageAdapter.setImageList(imageList);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve images.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occurred during networking.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleLayout() {
        if (isGrid) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            isGrid = false;
            Toast.makeText(this, "Switched to List View", Toast.LENGTH_SHORT).show();
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
            isGrid = true;
            Toast.makeText(this, "Switched to Grid View", Toast.LENGTH_SHORT).show();
        }
    }
}
