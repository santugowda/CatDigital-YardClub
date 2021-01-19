package com.example.catdigital_yardclub.ui.activerental

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActiveRentalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Active rental Fragment"
    }
    val text: LiveData<String> = _text
}