package com.alperb.yoyocinema.model

import com.alperb.yoyocinema.core.common.extensions.orFalse
import com.alperb.yoyocinema.network.model.Country
import com.alperb.yoyocinema.network.model.Genre
import com.alperb.yoyocinema.network.model.Language
import com.alperb.yoyocinema.network.model.MovieDetail

class YoyoMovieDetail(
    val id: Int?,
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
    val budget: Int?
) {
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
}

