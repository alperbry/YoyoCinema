package com.alperb.yoyocinema.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    abstract val viewModel: VM

    @LayoutRes abstract fun getResourceLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getResourceLayoutId())
        binding.lifecycleOwner = this
        bindVariables()
    }

    open fun bindVariables() {
        // no-op
    }
}
