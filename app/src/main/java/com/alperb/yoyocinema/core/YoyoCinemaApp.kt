package com.alperb.yoyocinema.core

import android.app.Application
import com.alperb.yoyocinema.di.DaggerAppComponent

class YoyoCinemaApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
