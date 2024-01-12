package com.soopeach.thethethe_android.data.network

import com.soopeach.thethethe_android.model.couple.CoupleRequest
import com.soopeach.thethethe_android.model.couple.CoupleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CoupleAPI {

    @GET("api/couples")
    suspend fun getCoupleInfo(
        @Header("Authorization") token: String
    ): CoupleResponse

    @POST("api/couples")
    suspend fun createCouple(
        @Header("Authorization") token: String,
        @Body coupleRequest: CoupleRequest
    ): String

    @POST("api/couples/{secretId}")
    suspend fun joinCouple(
        @Header("Authorization") token: String,
        @Path("secretId") secretId: String
    ): String

    @GET("api/couples/ranking")
    suspend fun getRanking(
        @Header("Authorization") token: String
    ): List<CoupleResponse>

}