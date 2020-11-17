package com.alperb.yoyocinema.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorite_movies_table")
data class MovieOverviewEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "creation_date")
    val creationDate: Long = System.currentTimeMillis()

) {
    constructor(yoyoMovieOverview: YoyoMovieOverview) : this(
        yoyoMovieOverview.id,
        yoyoMovieOverview.title,
        yoyoMovieOverview.posterPath,
        yoyoMovieOverview.adult,
        yoyoMovieOverview.voteAverage,
        yoyoMovieOverview.releaseDate
    )
}
