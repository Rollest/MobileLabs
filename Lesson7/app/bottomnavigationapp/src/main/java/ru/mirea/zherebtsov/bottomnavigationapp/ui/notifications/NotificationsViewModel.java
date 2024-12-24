package ru.mirea.zherebtsov.bottomnavigationapp.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.zherebtsov.bottomnavigationapp.R;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> title;
    private final MutableLiveData<String> text;
    private final MutableLiveData<Integer> imageResId;

    public NotificationsViewModel() {
        title = new MutableLiveData<>();
        text = new MutableLiveData<>();
        imageResId = new MutableLiveData<>();

        title.setValue("Арамис");
        text.setValue("Арамис — королевский мушкетёр, ваннский епископ, аббат монастыря в Нуази, генерал иезуитского ордена, персонаж произведений Александра Дюма: романов «Три мушкетёра», «Двадцать лет спустя» и «Виконт де Бражелон, или Десять лет спустя», пьес «Юность мушкетёров», «Мушкетёры» и «Узник Бастилии».");
        imageResId.setValue(R.drawable.aramis);
    }

    public LiveData<String> getTitle() {
        return title;
    }

    public LiveData<String> getText() {
        return text;
    }

    public LiveData<Integer> getImageResId() {
        return imageResId;
    }
}