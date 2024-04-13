package ru.mirea.vasilevmn.mireaproject.ui.files

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilesViewModel : ViewModel() {
    val path = MutableLiveData<String>()
    //val publicPath: LiveData<String> = path
}