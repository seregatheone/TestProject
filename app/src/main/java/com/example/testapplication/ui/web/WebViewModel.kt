package com.example.testapplication.ui.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {
    val webViewClient by lazy{ MyWebViewClient()}
    var currentWebUrl = "https://jsonplaceholder.typicode.com"
}