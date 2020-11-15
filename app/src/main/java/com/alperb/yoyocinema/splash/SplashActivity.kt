package com.alperb.yoyocinema.splash

import android.os.Bundle
import android.os.PersistableBundle
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseActivity
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.ActivitySplashBinding
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    @Inject
    override lateinit var viewModel: SplashViewModel

    override fun getResourceLayoutId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        (application as YoyoCinemaApp).appComponent.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

}
