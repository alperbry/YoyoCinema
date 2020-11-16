package com.alperb.yoyocinema.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alperb.yoyocinema.core.common.loading.LoadingOwner

abstract class BaseViewModel : ViewModel(), LoadingOwner {
    override val loadingObservableList: List<LiveData<*>> = listOf()

}
