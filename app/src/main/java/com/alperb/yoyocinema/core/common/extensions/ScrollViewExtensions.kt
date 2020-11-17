package com.alperb.yoyocinema.core.common.extensions

import androidx.core.widget.NestedScrollView

fun NestedScrollView.onScrollEnd(block: () -> Unit) {
    setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (canScrollVertically(1).not()) {
            block.invoke()
        }
    })
}
