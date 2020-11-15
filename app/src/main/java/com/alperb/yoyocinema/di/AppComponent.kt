package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.MainActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: MainActivity)
}
