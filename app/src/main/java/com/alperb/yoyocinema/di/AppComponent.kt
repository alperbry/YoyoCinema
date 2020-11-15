package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.feature.splash.SplashActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatcherModule::class, NetworkModule::class, AppSubComponents::class, HandlerModule::class])
interface AppComponent {

    fun homeComponent(): HomeComponent.Factory

    fun inject(activity: SplashActivity)

}

@Module(subcomponents = [HomeComponent::class])
class AppSubComponents
