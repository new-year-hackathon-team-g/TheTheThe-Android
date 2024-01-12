package com.soopeach.thethethe_android.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "http://thethethe.duckdns.org/"

    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
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

    fun getVideoApi(): VideoAPI {
        return client.create(VideoAPI::class.java)
    }


}