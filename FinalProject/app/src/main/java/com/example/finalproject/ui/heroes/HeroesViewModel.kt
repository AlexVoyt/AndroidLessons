package com.example.finalproject.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeroesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is heroes Fragment"
    }

    val text: LiveData<String> = _text
}