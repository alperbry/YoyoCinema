package com.alperb.yoyocinema.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alperb.yoyocinema.core.common.ToolbarModel
import com.alperb.yoyocinema.core.common.loading.LoadingOwner

abstract class BaseViewModel : ViewModel(), LoadingOwner {
    /**
     * List for each BaseViewModel implementation to register state emitting observables.
     */
    override val loadingObservableList: List<LiveData<*>> = listOf()

    open val toolbarModel = ToolbarModel()
}
