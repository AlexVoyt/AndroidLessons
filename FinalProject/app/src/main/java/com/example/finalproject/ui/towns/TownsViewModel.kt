package com.example.finalproject.ui.towns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TownsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is towns Fragment"
    }
    val text: LiveData<String> = _text
}