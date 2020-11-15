package com.alperb.yoyocinema.core.network


interface SuccessHandler {

    suspend fun <T> handleResponse(result: T): Response.Success<T>
}

class DefaultSuccessHandler : SuccessHandler {

    override suspend fun <T> handleResponse(result: T): Response.Success<T> {
        return Response.Success(result)
    }
}
