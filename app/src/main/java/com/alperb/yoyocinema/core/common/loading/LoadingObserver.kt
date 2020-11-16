package com.alperb.yoyocinema.core.common.loading

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.alperb.yoyocinema.core.common.UIState

interface LoadingObserver {

    fun startObserving(lifecycleOwner: LifecycleOwner, loadingOwner: LoadingOwner, activity: FragmentActivity)
}

class DefaultLoadingObserver : LoadingObserver {

    override fun startObserving(lifecycleOwner: LifecycleOwner, loadingOwner: LoadingOwner, activity: FragmentActivity) {
        loadingOwner.loadingObservableList.forEach {
            it.observe(lifecycleOwner) {
                if (it is UIState.Loading) {
                    getOrCreateLoading(activity).show(activity.supportFragmentManager, LoadingFragment.TAG)
                } else {
                    getOrCreateLoading(activity).dismiss()
                }
            }
        }
    }

    private fun getOrCreateLoading(activity: FragmentActivity): LoadingFragment {
        return activity.supportFragmentManager.findFragmentByTag(LoadingFragment.TAG) as? LoadingFragment
            ?: LoadingFragment()
    }
}
