package com.example.movieapp_w_compose.retrofit

import com.example.movieapp_w_compose.data.ConstValues
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalReq = chain.request()
        val reqBuilder = originalReq.newBuilder()
            .header("Authorization", "Bearer ${ConstValues.API_TOKEN}")
        val request = reqBuilder.build()

        return chain.proceed(request)
    }
}