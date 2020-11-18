package com.alperb.yoyocinema.feature.movie.usecase

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.db.FavoriteMoviesRepository
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

class CheckFavoriteMovieUseCase @Inject constructor(
    val repository: FavoriteMoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun checkFavoriteMovie(movieId: Int): UIState<Boolean> {
        return handleResponse(repository.getFavoriteMovie(movieId)) {
            it != null
        }
    }
}
