package com.example.photoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.photoapp.data.remote.ApiService
import com.example.photoapp.data.remote.PhotoPagingSource
import com.example.photoapp.model.Photo
import kotlinx.coroutines.flow.Flow

class PhotoViewModel(
    private val api: ApiService
) : ViewModel() {

    val photos: Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PhotoPagingSource(api)
        }
    ).flow.cachedIn(viewModelScope)
}