package com.alperb.yoyocinema.di

import android.app.Application
import androidx.room.Room
import com.alperb.yoyocinema.core.YoyoCinemaApp
import com.alperb.yoyocinema.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, MoviesDatabase::class.java, "movies-db").build()

    @Singleton
    @Provides
    fun provideMoviesDatabaseDao(database: MoviesDatabase) = database.moviesDao

}
