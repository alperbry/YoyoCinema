package com.alperb.yoyocinema.core.common.recycler

import androidx.annotation.LayoutRes

interface RecyclerModelWrapper {

    val presentation: RecyclerComponentPresentation
    @LayoutRes fun getLayoutId(): Int
}