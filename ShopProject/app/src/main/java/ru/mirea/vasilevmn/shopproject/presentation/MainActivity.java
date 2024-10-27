package ru.mirea.vasilevmn.shopproject.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.vasilevmn.shopproject.data.repository.ProductRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.data.repository.UserRepositoryImpl;
import ru.mirea.vasilevmn.shopproject.domain.models.Product;
import ru.mirea.vasilevmn.shopproject.domain.repository.ProductRepository;
import ru.mirea.vasilevmn.shopproject.domain.repository.UserRepository;
import ru.mirea.vasilevmn.shopproject.domain.usecases.GetAllProductsUseCase;
import ru.mirea.vasilevmn.shopproject.domain.usecases.GetUserWishedProductsUseCase;
import ru.mirea.vasilevmn.shopproject.domain.usecases.SignInUseCase;
import ru.mirea.vasilevmn.shopproject.domain.usecases.SignUpUseCase;
import ru.mirea.vasilevmn.shopproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText nameInput = binding.textInputName;
        EditText emailInput = binding.textInputEmail;
        EditText passwordInput = binding.textInputPassword;
        Button signInBtn = binding.buttonSignIn;
        Button signUpBtn = binding.buttonSignUp;
        TextView signInResult = binding.textViewSignInRes;
        TextView signUpResult = binding.textViewSignUpRes;
        Button getAllProductsBtn = binding.buttonGetAllProducts;
        TextView allProductsResult = binding.textViewAllProducts;
        EditText userIdInput = binding.editTextUserWishedProducts;
        Button getUserWishedProductsBtn = binding.buttonGetUsersWishedProducts;
        TextView userWishedProductsResult = binding.textViewUserWishedProducts;

        signInBtn.setOnClickListener(view -> {
            UserRepository userRepository = new UserRepositoryImpl();
           boolean result = new SignInUseCase(userRepository).execute(emailInput.getText().toString(),
                   passwordInput.getText().toString());
            signInResult.setText(result? "Вход прошел успешно" : "Вход провалился");
        });

        signUpBtn.setOnClickListener(view -> {
            UserRepository userRepository = new UserRepositoryImpl();
            boolean result = new SignUpUseCase(userRepository).execute(nameInput.getText().toString(),
                    emailInput.getText().toString(), passwordInput.getText().toString());
            signUpResult.setText(result? "Регистрация прошла успешно" : "Регистрация провалилась");
        });

        getAllProductsBtn.setOnClickListener(view -> {
            ProductRepository productRepository = new ProductRepositoryImpl();
            List<Product> Products = new GetAllProductsUseCase(productRepository).execute();
            allProductsResult.setText("");
            for (Product Product : Products) {
                String ProductString = Product.toString();
                allProductsResult.append(ProductString);
            }
        });

        getUserWishedProductsBtn.setOnClickListener(view -> {
            UserRepository userRepository = new UserRepositoryImpl();
            List<Integer> favouriteProducts = new GetUserWishedProductsUseCase(userRepository).execute(Integer.parseInt(userIdInput.getText().toString()));
            userWishedProductsResult.setText("");
            for (Integer favourite : favouriteProducts) {
                userWishedProductsResult.append(favourite.toString());
                userWishedProductsResult.append("\n");
            }
        });
    }
}