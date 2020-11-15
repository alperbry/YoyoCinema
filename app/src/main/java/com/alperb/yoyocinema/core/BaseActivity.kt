package com.alperb.yoyocinema.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    abstract val viewModel: VM

    @LayoutRes abstract fun getResourceLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = DataBindingUtil.setContentView(this, getResourceLayoutId())
        bindVariables()
        binding.lifecycleOwner = this
    }

    open fun bindVariables() {
        // no-op
    }
}
