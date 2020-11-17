package com.alperb.yoyocinema.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alperb.yoyocinema.model.MovieOverviewEntity

@Dao
interface MoviesDatabaseDao {

    @Insert
    suspend fun insert(movie: MovieOverviewEntity): Long?

    @Delete
    suspend fun delete(movie: MovieOverviewEntity): Int?

    @Query("SELECT * from favorite_movies_table WHERE id = :key")
    suspend fun get(key: Int): MovieOverviewEntity?

    @Query("SELECT * from favorite_movies_table ORDER BY creation_date DESC")
    suspend fun getAll(): List<MovieOverviewEntity>?
}
