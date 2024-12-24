package ru.mirea.vasilev.bottomnavigationapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.vasilev.bottomnavigationapp.R;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> title;
    private final MutableLiveData<String> text;
    private final MutableLiveData<Integer> imageResId;

    public HomeViewModel() {
        title = new MutableLiveData<>();
        text = new MutableLiveData<>();
        imageResId = new MutableLiveData<>();

        title.setValue("Ато́с, он же — Оливье, граф де Ла Фер");
        text.setValue("Ато́с, он же — Оливье, граф де Ла Фер — королевский мушкетёр, персонаж ряда произведений Александра Дюма: романов «Три мушкетёра», «Двадцать лет спустя» и «Виконт де Бражелон, или Десять лет спустя», пьес «Юность мушкетёров», «Мушкетёры» и «Узник Бастилии». Его имя присвоено астероиду Атос.");
        imageResId.setValue(R.drawable.atoc); // Замените на реальный идентификатор картинки
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