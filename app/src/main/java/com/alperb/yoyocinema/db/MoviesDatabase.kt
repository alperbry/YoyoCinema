package com.alperb.yoyocinema.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alperb.yoyocinema.model.MovieOverviewEntity

@Database(entities = [MovieOverviewEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        "favorite_movies_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
