package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.core.common.DebouncedSingleJobHandler
import dagger.Binds
import dagger.Module

@Module
abstract class HandlerModule {

    @Binds
    abstract fun provideDebouncedSingleJobHandler(jobHandler: DebouncedSingleJobHandler): DebouncedSingleJobHandler
}
