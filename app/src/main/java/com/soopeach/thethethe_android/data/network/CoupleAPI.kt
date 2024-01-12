package com.soopeach.thethethe_android.data.network

import com.soopeach.thethethe_android.model.couple.CoupleRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CoupleAPI {

    @POST("api/couples")
    suspend fun createCouple(
        @Header("Authorization") token: String,
        @Body coupleRequest: CoupleRequest
    ): String

}