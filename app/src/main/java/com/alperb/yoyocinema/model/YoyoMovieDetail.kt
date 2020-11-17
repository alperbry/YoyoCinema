package com.alperb.yoyocinema.model

import android.os.Parcelable
import com.alperb.yoyocinema.BuildConfig
import com.alperb.yoyocinema.core.common.extensions.orFalse
import com.alperb.yoyocinema.network.model.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class YoyoMovieDetail(
    val id: Int,
    val title: String?,
    val posterUrl: String?,
    val adult: Boolean,
    val voteAverage: Double?,
    val releaseDate: String?,
    val originalTitle: String?,
    val spokenLanguages: List<Language>,
    val originalLanguage: String?,
    val productionCountries: List<Country>,
    val genres: List<Genre>,
    val imdbId: String?,
    val overview: String?,
    val budget: Int?,
    val castList: List<YoyoCast>? = null
) : Parcelable {
    constructor(movieDetail: MovieDetail) : this(
        id = movieDetail.id,
        title = movieDetail.title,
        posterUrl = movieDetail.poster_path,
        adult = movieDetail.adult.orFalse(),
        voteAverage = movieDetail.vote_average,
        releaseDate = movieDetail.release_date,
        originalTitle = movieDetail.originalTitle,
        spokenLanguages = movieDetail.spokenLanguages?.mapNotNull { it }.orEmpty(),
        originalLanguage = movieDetail.originalLanguage,
        productionCountries = movieDetail.productionCountries?.mapNotNull { it }.orEmpty(),
        genres = movieDetail.genres?.mapNotNull { it }.orEmpty(),
        imdbId = movieDetail.imdbId,
        overview = movieDetail.overview,
        budget = movieDetail.budget
    )

    constructor(movieDetail: MovieDetail, castList: List<Cast?>?) : this(
        id = movieDetail.id,
        title = movieDetail.title,
        posterUrl = "${BuildConfig.BASE_IMAGE_URL}${movieDetail.poster_path}",
        adult = movieDetail.adult.orFalse(),
        voteAverage = movieDetail.vote_average,
        releaseDate = movieDetail.release_date,
        originalTitle = movieDetail.originalTitle,
        spokenLanguages = movieDetail.spokenLanguages?.mapNotNull { it }.orEmpty(),
        originalLanguage = movieDetail.originalLanguage,
        productionCountries = movieDetail.productionCountries?.mapNotNull { it }.orEmpty(),
        genres = movieDetail.genres?.mapNotNull { it }.orEmpty(),
        imdbId = movieDetail.imdbId,
        overview = movieDetail.overview,
        budget = movieDetail.budget,
        castList = castList?.mapNotNull { YoyoCast.newInstance(it) }
    )
}

