package com.alperb.yoyocinema.core.common


sealed class UIState<out T> {

    class Success<T>(val data: T) : UIState<T>()

    class Failure(
        val errorMessage: String? = null,
        val errorCode: String? = null,
        val tag: Any? = null
    ) : UIState<Nothing>()

    object Loading : UIState<Nothing>()

}
