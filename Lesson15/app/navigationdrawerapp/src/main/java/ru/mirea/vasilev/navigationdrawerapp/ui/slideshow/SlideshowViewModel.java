package ru.mirea.vasilev.navigationdrawerapp.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.vasilev.navigationdrawerapp.R;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> title;
    private final MutableLiveData<String> text;
    private final MutableLiveData<Integer> imageResId;

    public SlideshowViewModel() {
        title = new MutableLiveData<>();
        text = new MutableLiveData<>();
        imageResId = new MutableLiveData<>();

        title.setValue("Сакура Учиха");
        text.setValue("Сакура Учиха (яп. うちはサクラ, Учиха Сакура, урождённая Харуно (яп. 春野) — куноичи из Скрытого Листа. Став частью Команды 7, Сакура быстро осознаёт свою неподготовленность к суровой жизни шиноби. Тем не менее, после прохождения тренировок под руководством Саннина Цунаде, она преодолевает это и становится квалифицированным ниндзя-медиком. В конце манги становится женой Саске, а позже рожает от него дочь Сараду.");
        imageResId.setValue(R.drawable.sakura);
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