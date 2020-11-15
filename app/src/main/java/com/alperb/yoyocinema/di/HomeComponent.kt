package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.feature.home.HomeActivity
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)

}
