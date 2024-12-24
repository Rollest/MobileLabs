package ru.mirea.vasilev.retrofitapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private Context context;
    private List<Todo> todoList;
    private ApiService apiService;

    public TodoAdapter(Context context, List<Todo> todoList, ApiService apiService) {
        this.context = context;
        this.todoList = todoList;
        this.apiService = apiService;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Инфлейт макет элемента списка
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        // Получаем текущий объект Todo
        Todo todo = todoList.get(position);

        // Устанавливаем данные в элементы управления
        holder.textViewTitle.setText(todo.getTitle());

        // Устанавливаем слушатель для CheckBox
        holder.checkBoxCompleted.setOnCheckedChangeListener(null);  // Отвязываем предыдущий слушатель
        holder.checkBoxCompleted.setChecked(todo.isCompleted());
        holder.checkBoxCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Обновляем локальную модель
                todo.setCompleted(isChecked);

                // Создаем карту полей для обновления
                Map<String, Object> fields = new HashMap<>();
                fields.put("completed", isChecked);

                // Отправляем PATCH-запрос для обновления статуса
                Call<Todo> call = apiService.updateTodo(todo.getId(), fields);
                call.enqueue(new Callback<Todo>() {
                    @Override
                    public void onResponse(Call<Todo> call, Response<Todo> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Статус обновлен", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Ошибка при обновлении", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Todo> call, Throwable t) {
                        Toast.makeText(context, "Не удалось связаться с сервером", Toast.LENGTH_SHORT).show();
                        Log.e("TodoAdapter", "Ошибка при обновлении", t);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    // ViewHolder для элементов списка
    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        CheckBox checkBoxCompleted;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
        }
    }
}
