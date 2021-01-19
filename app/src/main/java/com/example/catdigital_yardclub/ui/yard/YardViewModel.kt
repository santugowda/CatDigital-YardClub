package com.example.catdigital_yardclub.ui.yard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Yard Fragment"
    }
    val text: LiveData<String> = _text
}