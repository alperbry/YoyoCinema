package com.alperb.yoyocinema.core.common.loading

import androidx.lifecycle.LiveData

interface LoadingOwner {

    val loadingObservableList: List<LiveData<*>>
}
