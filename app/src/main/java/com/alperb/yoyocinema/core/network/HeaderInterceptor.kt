package com.alperb.yoyocinema.core.network

import okhttp3.Interceptor
import okhttp3.Response

private const val KEY_CONTENT_TYPE = "Content-Type"
private const val VALUE_CONTENT_TYPE = "application/json"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder().apply {
            header(KEY_CONTENT_TYPE, VALUE_CONTENT_TYPE)
        }
        return chain.proceed(newRequestBuilder.build())
    }
}
