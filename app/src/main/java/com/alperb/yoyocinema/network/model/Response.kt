package com.alperb.yoyocinema.network.model

import com.squareup.moshi.Json

data class MovieOverview(
    @Json(name = "id") val id: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "release_date") val releaseDate: String?
)
