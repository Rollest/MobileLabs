package ru.mirea.vasilevmn.mireaproject.ui.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {
    private val _url = MutableLiveData<String>().apply {
        value = "https://www.youtube.com"
    }
    val webView: LiveData<String> = _url
}