package com.alperb.yoyocinema.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

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

@JsonClass(generateAdapter = true)
data class MovieDetail(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "release_date") val release_date: String?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "spoken_languages") val spokenLanguages: List<Language?>?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "production_countries") val productionCountries: List<Country?>?,
    @Json(name = "genres") val genres: List<Genre?>?,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "budget") val budget: Int?
)

@JsonClass(generateAdapter = true)
data class MovieCredits(
    @Json(name = "id") val id: Int?,
    @Json(name = "cast") val castList: List<Cast?>?
)

@JsonClass(generateAdapter = true)
data class Cast(
    @Json(name = "name") val name: String?,
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "profile_path") val profile_path: String?,
    @Json(name = "character") val character: String?
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Language(
    @Json(name = "iso_639_1") val iso: String?,
    @Json(name = "name") val name: String?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Country(
    @Json(name = "iso_3166_1") val iso: String?,
    @Json(name = "name") val name: String?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Genre(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?
) : Parcelable
