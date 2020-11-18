package com.alperb.yoyocinema.core.common.error

import com.alperb.yoyocinema.core.common.UIState

data class ErrorModel(
    val state: UIState.Failure,
    val action: (() -> Unit)? = null
) {
    val actionButtonVisibility: Boolean = (action != null)
    val message = state.errorMessage ?: "Something went wrong."
}
