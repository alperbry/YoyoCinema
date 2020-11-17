package com.alperb.yoyocinema.core.common.util

import android.content.Context
import javax.inject.Inject

interface DisplayHelper {
    val context: Context

    fun getScreenWidthInPx(): Int
}

class DefaultDisplayHelper @Inject constructor(
    override val context: Context
) : DisplayHelper {

    override fun getScreenWidthInPx(): Int = getDisplayMetrics().widthPixels

    private fun getDisplayMetrics() = context.resources.displayMetrics
}
