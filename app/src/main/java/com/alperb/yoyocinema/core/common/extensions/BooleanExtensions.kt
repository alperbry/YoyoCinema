package com.alperb.yoyocinema.core.common.extensions

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true