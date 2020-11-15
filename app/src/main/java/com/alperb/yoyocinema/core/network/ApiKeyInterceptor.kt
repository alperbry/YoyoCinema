package com.alperb.yoyocinema.core.network

import com.alperb.yoyocinema.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val KEY_API_KEY = "api_key"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url

        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter(KEY_API_KEY, BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}
