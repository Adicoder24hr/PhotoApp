package com.example.photoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoapp.data.remote.ApiService

class PhotoViewModelFactory(
    private val api: ApiService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)){
            return PhotoViewModel(api) as T
        }
         throw IllegalArgumentException("Unknown ViewModel class")
    }
}