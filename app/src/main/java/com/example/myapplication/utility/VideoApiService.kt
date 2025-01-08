package com.example.myapplication.utility

import com.example.myapplication.models.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApiService {
//    ?category=recorded video&limit=10&ordering=-id
    @GET("api/v2/video/all/")
    suspend fun getLatestVideos(
        @Query("category") category: String,
        @Query("limit") limit: Int,
        @Query("ordering") ordering: String="-id"
    ): VideoResponse
}