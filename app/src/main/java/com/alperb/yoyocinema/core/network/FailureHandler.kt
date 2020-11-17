package com.alperb.yoyocinema.core.network

import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

interface FailureHandler {
    suspend fun handleResponse(throwable: Throwable): Response.Failure
}

class DefaultFailureHandler : FailureHandler {
    override suspend fun handleResponse(throwable: Throwable): Response.Failure {
        return when (throwable) {
            //todo add for room database
            is HttpException -> Response.Failure(throwable.code(), throwable.message())
            else -> Response.Failure()
        }
    }
}
