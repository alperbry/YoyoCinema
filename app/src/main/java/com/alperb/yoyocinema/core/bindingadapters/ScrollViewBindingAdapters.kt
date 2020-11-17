package com.alperb.yoyocinema.core.bindingadapters

import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import com.alperb.yoyocinema.core.common.extensions.onScrollEnd

@BindingAdapter("onBottomReached")
fun NestedScrollView.onReachedBottom(block: () -> Unit) {
    onScrollEnd(block)
}
