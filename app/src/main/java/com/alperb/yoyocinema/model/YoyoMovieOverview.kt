package com.alperb.yoyocinema.model

import com.alperb.yoyocinema.network.model.MovieOverview

class YoyoMovieOverview(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val adult: Boolean?,
    val voteAverage: Double?,
    val releaseDate: String?
) {
    companion object {
        fun getInstance(model: MovieOverview): YoyoMovieOverview? {
            if (model.id == null || model.title == null) {
                return null
            }

            return YoyoMovieOverview(
                model.id,
                model.title,
                model.poster_path,
                model.adult,
                model.vote_average,
                model.release_date
            )
        }
    }
}
