package com.alperb.yoyocinema.core.common

import androidx.annotation.StringRes
import com.alperb.yoyocinema.R

data class ToolbarModel(
    val navIcon: Int? = R.drawable.ic_baseline_arrow_back_24,
    @StringRes val titleRes: Int? = null
)
