package com.alperb.yoyocinema.feature.home

import android.os.Bundle
import android.os.PersistableBundle
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseActivity
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.ActivityHomeBinding
import com.alperb.yoyocinema.di.HomeComponent
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    lateinit var homeComponent: HomeComponent

    @Inject
    override lateinit var viewModel: HomeViewModel

    override fun getResourceLayoutId() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        homeComponent = (application as YoyoCinemaApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

}
