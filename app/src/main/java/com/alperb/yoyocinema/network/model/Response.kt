package com.alperb.yoyocinema.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSearchResponse(
    @Json(name = "page") val page: Int?,
    @Json(name = "total_results") val totalResults: Int?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "results") val results: List<MovieOverview?>?
)

@JsonClass(generateAdapter = true)
data class MovieOverview(
    @Json(name = "id") val id: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "release_date") val release_date: String?
)
