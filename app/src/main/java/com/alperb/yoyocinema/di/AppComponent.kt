package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatcherModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}
