package com.alperb.yoyocinema.core.common

import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

// fixme get from dependency graph
object DebouncedSingleJobHandler {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private var currentJob: Job? = null

    fun post(block: () -> Unit, delay: Long) {
        currentJob?.cancel()
        currentJob = coroutineScope.launch(Dispatchers.Main) {
            delay(delay)
            block.invoke()
        }
    }
}
