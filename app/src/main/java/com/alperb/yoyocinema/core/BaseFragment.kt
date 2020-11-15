package com.alperb.yoyocinema.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    lateinit var binding: B

    abstract val viewModel: VM

    @LayoutRes abstract fun getResourceLayoutId(): Int

    open fun bindVariables() {
        // no-op
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
}