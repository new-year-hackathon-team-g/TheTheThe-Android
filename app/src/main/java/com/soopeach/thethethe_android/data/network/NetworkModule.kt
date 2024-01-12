package com.soopeach.thethethe_android.data.network

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.GsonBuilder
import com.soopeach.thethethe_android.model.RecommendationVideo
import com.soopeach.thethethe_android.model.login.LoginRequest
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.login.LoginResponse
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "http://thethethe.duckdns.org/"

    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .client(
            OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        ).build()

    private val storage = Firebase.storage.reference.child("images/${System.currentTimeMillis()}.jpg")

    suspend fun uploadImage(byteArray: ByteArray): String {
        val task = storage.putBytes(byteArray).await()
        return task.storage.downloadUrl.await().toString()
    }

    private fun getVideoApi(): VideoAPI {
        return client.create(VideoAPI::class.java)
    }

    private fun getUserApi(): UserAPI {
        return client.create(UserAPI::class.java)
    }

    suspend fun getVideos(): List<RecommendationVideo> {
        return getVideoApi().getVideos()
    }

    suspend fun postLogin(loginRequest: LoginRequest): LoginResponse {
        return getUserApi().postLogin(loginRequest)
    }

    suspend fun postSignUp(signUpRequest: SignUpRequest): Boolean {
        return getUserApi().postSignUp(signUpRequest).contains("success")
    }


}