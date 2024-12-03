package ru.mirea.vasilevmn.shopproject.presentation.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import ru.mirea.vasilevmn.shopproject.domain.auth.FirebaseAuthHelper;

public class LoginViewModel extends AndroidViewModel {
    private final FirebaseAuthHelper authHelper;
    private final SharedPreferences sharedPreferences;

    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LoginViewModel(Application application) {
        super(application);
        authHelper = new FirebaseAuthHelper();
        sharedPreferences = application.getSharedPreferences("AppPrefs", Application.MODE_PRIVATE);
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void signIn(String email, String password) {
        authHelper.signIn(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = authHelper.getCurrentUser();
                if (user != null) {
                    saveUserEmail(email);
                    userLiveData.postValue(user);
                }
            } else {
                errorLiveData.postValue("Ошибка авторизации");
            }
        });
    }

    public void signUp(String email, String password) {
        authHelper.signUp(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                signIn(email, password);
            } else {
                errorLiveData.postValue("Ошибка регистрации");
            }
        });
    }

    private void saveUserEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_email", email);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return authHelper.getCurrentUser() != null;
    }
}
