package com.example.apitest2.adout.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class PosterViewModel(
    url: String
): ViewModel() {

    private val urlLiveData = MutableLiveData<String>(url)
    fun observeUrl(): LiveData<String> = urlLiveData
}