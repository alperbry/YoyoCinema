package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatcherModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: SplashActivity)
}
