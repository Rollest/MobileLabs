package ru.mirea.vasilev.navigationdrawerapp.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.vasilev.navigationdrawerapp.R;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> title;
    private final MutableLiveData<String> text;
    private final MutableLiveData<Integer> imageResId;

    public GalleryViewModel() {
        title = new MutableLiveData<>();
        text = new MutableLiveData<>();
        imageResId = new MutableLiveData<>();

        title.setValue("Наруто Узумаки - Джинчурики Курамы, Седьмой Хокаге и главный герой одноимённого аниме и манги.");
        text.setValue("Наруто Узумаки (яп. うずまきナルト, Узумаки Наруто) — шиноби Деревни Скрытого Листа. Главный персонаж вселенной. В день своего рождения стал джинчуурики Девятихвостого Демона-Лиса — судьба, из-за которой он стал изгоем для большей части людей в Конохе на протяжении всего своего детства. После присоединения к команде Какаши, Наруто упорно трудился, чтобы получить признание всех в деревне и исполнить свою заветную мечту стать Хокаге. В последующие годы, благодаря многим трудностям и испытаниям, он стал способным ниндзя, которого считали героем Конохагакуре, и после во всем мире, он стал известен как Герой Скрытого Листа (яп. 木ノ葉隠れの英雄, Конохагакуре но Эйю). Вскоре, он оказался одним из ключевых факторов победы в Четвертой Мировой Войне Шиноби, что в конце-концов привело его к достижению своей мечты, когда он стал Седьмым Хокаге (яп. 七代目火影, Нанадаймэ Хокаге; досл. Тень Огня Седьмого Поколения).");
        imageResId.setValue(R.drawable.naruto);
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