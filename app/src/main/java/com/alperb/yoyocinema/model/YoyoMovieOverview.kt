package com.alperb.yoyocinema.model

import androidx.annotation.DrawableRes
import com.alperb.yoyocinema.BuildConfig
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.common.extensions.orFalse
import com.alperb.yoyocinema.network.model.MovieOverview

class YoyoMovieOverview(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val adult: Boolean = false,
    val voteAverage: Double?,
    val releaseDate: String?,
    @DrawableRes val placeholderResource: Int? = R.drawable.ic_launcher_background
) {
    constructor(movieDetail: YoyoMovieDetail) : this(
        movieDetail.id,
        movieDetail.title.orEmpty(),
        movieDetail.posterUrl,
        movieDetail.adult,
        movieDetail.voteAverage,
        movieDetail.releaseDate
    )

    companion object {
        fun getInstance(model: MovieOverview): YoyoMovieOverview? {
            if (model.id == null || model.title == null) {
                return null
            }

            return YoyoMovieOverview(
                model.id,
                model.title,
                "${BuildConfig.BASE_IMAGE_URL}${model.poster_path}",
                model.adult.orFalse(),
                model.vote_average,
                model.release_date
            )
        }
    }
}
