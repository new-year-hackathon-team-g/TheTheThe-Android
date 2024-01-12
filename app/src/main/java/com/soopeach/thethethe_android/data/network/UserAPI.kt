package com.soopeach.thethethe_android.data.network

import com.soopeach.thethethe_android.model.login.LoginRequest
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("signup")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): String

    @POST("login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): LoginResponse

}