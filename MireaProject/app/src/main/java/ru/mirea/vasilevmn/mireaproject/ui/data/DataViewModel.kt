package ru.mirea.vasilevmn.mireaproject.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val _textTitle = MutableLiveData<String>().apply {
        value = "Аддитивные технологии"
    }

    private val _textDescription = MutableLiveData<String>().apply {
        value = "Аддитивные технологии — метод создания трёхмерных объектов, деталей или вещей путём послойного добавления материала\n" +
                "С помощью аддитивных технологий с помощью 3д принтеров можно печатать 3д-объекты из пластика, металла, бетона и других материалов."
    }

    val textViewTitle: LiveData<String> = _textTitle
    val textViewDescription: LiveData<String> = _textDescription
}