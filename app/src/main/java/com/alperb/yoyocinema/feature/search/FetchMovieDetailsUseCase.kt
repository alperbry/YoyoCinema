package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.network.repository.MoviesRepository
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    val repository: MoviesRepository
) : BaseUseCase() {

    suspend fun fetchMovieDetails(movieId: Int): UIState<YoyoMovieDetail?> {
        return handleResponse(repository.fetchMovieDetails(movieId)) { movieDetail ->
            movieDetail?.let { YoyoMovieDetail(it) }
        }
    }
}
