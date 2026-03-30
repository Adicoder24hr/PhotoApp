package com.example.photoapp.data.remote

import com.example.photoapp.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Photo>

}