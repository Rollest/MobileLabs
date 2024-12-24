package ru.mirea.zherebtsov.bottomnavigationapp.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.zherebtsov.bottomnavigationapp.R;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> title;
    private final MutableLiveData<String> text;
    private final MutableLiveData<Integer> imageResId;

    public DashboardViewModel() {
        title = new MutableLiveData<>();
        text = new MutableLiveData<>();
        imageResId = new MutableLiveData<>();

        title.setValue("Портос, он же — барон дю Валлон де Брасье де Пьерфон");
        text.setValue("Портос, он же — барон дю Валлон де Брасье де Пьерфон — королевский мушкетёр, персонаж произведений Александра Дюма: романов «Три мушкетёра», «Двадцать лет спустя» и «Виконт де Бражелон, или Десять лет спустя», пьес «Юность мушкетёров», «Мушкетёры» и «Узник Бастилии». Его имя присвоено астероиду Портос.");
        imageResId.setValue(R.drawable.partos);
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