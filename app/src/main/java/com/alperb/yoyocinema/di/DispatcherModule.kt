package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.core.coroutines.DefaultDispatcherProvider
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class DispatcherModule {

    @Binds
    abstract fun provideDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}