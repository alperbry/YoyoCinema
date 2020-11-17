package com.alperb.yoyocinema.feature.favorite

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.db.FavoriteMoviesRepository
import com.alperb.yoyocinema.model.MovieOverviewEntity
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase@Inject constructor(
    val repository: FavoriteMoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun removeFromFavorites(yoyoMovieOverview: YoyoMovieOverview) {
        repository.removeMovieFromFavorites(MovieOverviewEntity(yoyoMovieOverview))
    }
}
