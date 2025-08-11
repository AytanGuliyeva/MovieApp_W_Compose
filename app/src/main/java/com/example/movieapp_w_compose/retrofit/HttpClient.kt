package com.example.movieapp_w_compose.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpClient {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient : OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthTokenInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
}