package com.alperb.yoyocinema.core.common.loading

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.alperb.yoyocinema.core.common.UIState

interface LoadingObserver {

    fun startObserving(lifecycleOwner: LifecycleOwner, loadingOwner: LoadingOwner, fragmentManager: FragmentManager)
}

class DefaultLoadingObserver : LoadingObserver {

    /**
     * Observes registered UIState.Loading emitting observables and shows loading progress.
     */
    override fun startObserving(lifecycleOwner: LifecycleOwner, loadingOwner: LoadingOwner, fragmentManager: FragmentManager) {
        loadingOwner.loadingObservableList.forEach {
            it.observe(lifecycleOwner) {
                if (it is UIState.Loading) {
                    getOrCreateLoading(fragmentManager).show(fragmentManager, LoadingFragment.TAG)
                } else {
                    getOrCreateLoading(fragmentManager).dismiss()
                }
            }
        }
    }

    private fun getOrCreateLoading(fragmentManager: FragmentManager): LoadingFragment {
        return fragmentManager.findFragmentByTag(LoadingFragment.TAG) as? LoadingFragment
            ?: LoadingFragment()
    }
}
