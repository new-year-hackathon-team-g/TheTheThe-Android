package com.soopeach.thethethe_android.data.network

import com.soopeach.thethethe_android.model.login.LoginRequest
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.login.LoginResponse
import com.soopeach.thethethe_android.model.pop.PopResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @POST("api/signup")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): String

    @POST("api/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/pop/{count}")
    suspend fun postPop(
        @Header("Authorization") token: String,
        @Path("count") count: Int
    ): PopResponse

}