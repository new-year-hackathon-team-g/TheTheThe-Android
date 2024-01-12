package com.soopeach.thethethe_android.data.network

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.GsonBuilder
import com.soopeach.thethethe_android.model.RecommendationVideo
import com.soopeach.thethethe_android.model.login.LoginRequest
import com.soopeach.thethethe_android.model.SignUpRequest
import com.soopeach.thethethe_android.model.couple.CoupleRequest
import com.soopeach.thethethe_android.model.login.LoginResponse
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                })

                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        ).build()

    private val storage =
        Firebase.storage.reference.child("images/${System.currentTimeMillis()}.jpg")

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

    private fun getCoupleApi(): CoupleAPI {
        return client.create(CoupleAPI::class.java)
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

    suspend fun createCouple(token: String, coupleRequest: CoupleRequest): String {
        val result = getCoupleApi().createCouple(token, coupleRequest)
        println("커플 생성 $result")
        return result
    }


}