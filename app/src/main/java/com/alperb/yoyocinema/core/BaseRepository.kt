package com.alperb.yoyocinema.core

import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.core.network.FailureHandler
import com.alperb.yoyocinema.core.network.Response
import com.alperb.yoyocinema.core.network.SuccessHandler
import kotlinx.coroutines.withContext

abstract class BaseRepository(private val dispatcherProvider: DispatcherProvider) {

    abstract val successHandler: SuccessHandler
    abstract val failureHandler: FailureHandler

    suspend fun <T> apiCall(block: suspend () -> T?): Response<T?> {
        return withContext(dispatcherProvider.io) {
            try {
                successHandler.handleResponse(block.invoke())
            } catch (throwable: Throwable) {
                failureHandler.handleResponse(throwable)
            }
        }
    }

}
