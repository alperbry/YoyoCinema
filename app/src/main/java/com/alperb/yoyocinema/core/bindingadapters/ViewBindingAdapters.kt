package com.alperb.yoyocinema.core.bindingadapters

import android.view.View
import android.widget.ViewSwitcher
import androidx.databinding.BindingAdapter
import com.alperb.yoyocinema.core.common.UIState

@BindingAdapter("visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("uiState")
fun ViewSwitcher.setUIState(uiState: UIState<*>?) {
    displayedChild = if (uiState is UIState.Failure) {
        1
    } else {
        0
    }
}

@BindingAdapter("displayedChild")
fun ViewSwitcher.displayedChild(index: Int) {
    displayedChild = if (index == 1) {
        1
    } else {
        0
    }
}
