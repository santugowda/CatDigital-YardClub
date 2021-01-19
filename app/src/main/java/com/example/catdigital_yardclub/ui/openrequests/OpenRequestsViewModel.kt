package com.example.catdigital_yardclub.ui.openrequests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OpenRequestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Open requests Fragment"
    }
    val text: LiveData<String> = _text
}