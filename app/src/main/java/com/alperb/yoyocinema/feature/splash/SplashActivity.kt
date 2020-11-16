package com.alperb.yoyocinema.feature.splash

import android.content.Intent
import android.os.Bundle
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseActivity
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.databinding.ActivitySplashBinding
import com.alperb.yoyocinema.feature.home.HomeHostActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    @Inject
    override lateinit var viewModel: SplashViewModel

    override fun getResourceLayoutId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as YoyoCinemaApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        //fixme dummy navigation
        startActivity(Intent(this, HomeHostActivity::class.java))
    }

}
