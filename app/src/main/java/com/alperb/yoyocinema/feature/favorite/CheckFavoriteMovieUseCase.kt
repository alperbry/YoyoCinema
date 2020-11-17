package com.alperb.yoyocinema.feature.favorite

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

    suspend fun checkFavoriteMovie(yoyoMovieDetail: YoyoMovieDetail): UIState<Boolean> {
        return handleResponse(repository.getFavoriteMovie(yoyoMovieDetail.id)) {
            it != null
        }
    }
}
