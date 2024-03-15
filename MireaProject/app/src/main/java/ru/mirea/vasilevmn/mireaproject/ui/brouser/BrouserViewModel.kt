package ru.mirea.vasilevmn.mireaproject.ui.brouser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BrouserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is brouser Fragment"
    }
    val text: LiveData<String> = _text
}