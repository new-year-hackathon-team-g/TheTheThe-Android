package com.soopeach.thethethe_android.data.network

import com.soopeach.thethethe_android.model.RecommendationVideo
import retrofit2.http.GET

interface VideoAPI {

    @GET("api/videos")
    suspend fun getVideos(): List<RecommendationVideo>
}