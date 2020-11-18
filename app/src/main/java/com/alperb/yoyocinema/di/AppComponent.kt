package com.alperb.yoyocinema.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DispatcherModule::class,
    NetworkModule::class,
    AppSubComponents::class,
    HandlerModule::class,
    ViewModelFactoryModule::class,
    DatabaseModule::class,
    UtilityModule::class
])
interface AppComponent {

    fun homeComponent(): HomeComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}

@Module(subcomponents = [HomeComponent::class])
class AppSubComponents
