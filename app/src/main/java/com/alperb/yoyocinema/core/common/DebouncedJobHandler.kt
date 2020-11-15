package com.alperb.yoyocinema.core.common

import android.os.Handler
import android.os.Looper

// fixme get from dependency graph
object DebouncedSingleJobHandler {
    private val handler = Handler(Looper.getMainLooper())

    private var currentRunnable: Runnable? = null

    fun post(runnable: Runnable, delay: Long) {
        currentRunnable?.let { handler.removeCallbacks(it) }
        currentRunnable = runnable
        handler.postDelayed({
            runnable.run()
            currentRunnable = null
        }, delay)
    }
}
