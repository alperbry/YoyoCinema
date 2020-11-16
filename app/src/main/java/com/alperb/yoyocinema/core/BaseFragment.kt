package com.alperb.yoyocinema.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.common.loading.DefaultLoadingObserver
import com.alperb.yoyocinema.core.common.loading.LoadingFragment
import com.alperb.yoyocinema.core.common.loading.LoadingOwner
import com.alperb.yoyocinema.di.ViewModelFactory
import com.alperb.yoyocinema.feature.home.HomeFragment
import com.alperb.yoyocinema.feature.home.HomeViewModel
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    lateinit var binding: B

    lateinit var viewModel: VM

    abstract var viewModelFactory: ViewModelFactory

    @LayoutRes abstract fun getResourceLayoutId(): Int

    abstract fun getViewModelClazz(): Class<VM>

    val loadingObserver = DefaultLoadingObserver() //fixme add to di graph

    open fun bindVariables() {
        // no-op
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClazz())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getResourceLayoutId(), container, false)
        bindVariables()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingObserver.startObserving(this, viewModel, requireActivity())
        attachViewModelObservers()
    }

    open fun attachViewModelObservers() {
        // no-op
    }

}
