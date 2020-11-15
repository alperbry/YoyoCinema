package com.alperb.yoyocinema.core

import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.network.Response

abstract class BaseUseCase {

    fun <T, V> handleResponse(
        response: Response<T>,
        block: (T) -> V
    ): UIState<V> {
        return when (response) {
            is Response.Success -> UIState.Success(block.invoke(response.data))
            is Response.Failure -> UIState.Failure(
                response.errorMessage,
                response.errorCode.toString(),
                response.errorTag
            )
        }
    }
}
