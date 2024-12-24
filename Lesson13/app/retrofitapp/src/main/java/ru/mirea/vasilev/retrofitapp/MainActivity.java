package ru.mirea.vasilev.retrofitapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.vasilev.retrofitapp.TodoAdapter;
import ru.mirea.vasilev.retrofitapp.ApiService;
import ru.mirea.vasilev.retrofitapp.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private ProgressBar progressBar;

    private ApiService apiService; // Экземпляр ApiService

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем макет активности

        recyclerView = findViewById(R.id.recyclerViewTodos);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Инициализируем Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/") // Базовый URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Загружаем список дел
        fetchTodos();
    }

    private void fetchTodos() {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    List<Todo> todoList = response.body();

                    // Инициализируем адаптер и устанавливаем его для RecyclerView
                    todoAdapter = new TodoAdapter(MainActivity.this, todoList, apiService);
                    recyclerView.setAdapter(todoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Не удалось загрузить данные", Toast.LENGTH_SHORT).show();
            }
        });
    }
}