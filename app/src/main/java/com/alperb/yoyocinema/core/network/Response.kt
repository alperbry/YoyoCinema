package com.alperb.yoyocinema.core.network

sealed class Response<out T> {

    data class Success<out T>(val data: T) : Response<T>()

    data class Failure(
        val errorCode: Int? = null,
        val errorMessage: String? = null,
        val errorTag: Any? = null
    ) : Response<Nothing>()

}
