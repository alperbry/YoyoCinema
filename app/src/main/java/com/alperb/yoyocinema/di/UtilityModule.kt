package com.alperb.yoyocinema.di

import android.app.Application
import android.content.Context
import com.alperb.yoyocinema.core.common.util.DefaultDisplayHelper
import com.alperb.yoyocinema.core.common.util.DisplayHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilityModule {

    @Singleton
    @Provides
    fun provideScreenHelper(application: Application): DisplayHelper = DefaultDisplayHelper(application as Context)
}
