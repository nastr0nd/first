package com.example.bsd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class VModel:ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}