package com.alperb.yoyocinema.core

import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.core.network.Response
import kotlinx.coroutines.withContext

abstract class BaseUseCase(val dispatcherProvider: DispatcherProvider) {

    /**
     * Generic function to convert network response model into UI State models.
     */
    suspend fun <T, V> handleResponse(
        response: Response<T>,
        block: suspend (T) -> V
    ): UIState<V> {
        return when (response) {
            is Response.Success -> {
                UIState.Success(withContext(dispatcherProvider.default) {
                    block.invoke(response.data)}
                )
            }
            is Response.Failure -> UIState.Failure(
                response.errorMessage,
                response.errorCode.toString(),
                response.errorTag
            )
        }
    }
}
