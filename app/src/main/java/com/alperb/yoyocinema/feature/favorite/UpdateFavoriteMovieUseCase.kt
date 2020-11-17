package com.alperb.yoyocinema.feature.favorite

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.db.FavoriteMoviesRepository
import com.alperb.yoyocinema.model.MovieOverviewEntity
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

class UpdateFavoriteMovieUseCase @Inject constructor(
    val setMovieAsFavoriteUseCase: SetMovieAsFavoriteUseCase,
    val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun updateFavoriteMovie(yoyoMovieOverview: YoyoMovieOverview, isSelected: Boolean) {
        if (isSelected) {
            setMovieAsFavoriteUseCase.setMovieAsFavorite(yoyoMovieOverview)
        } else {
            removeMovieFromFavoritesUseCase.removeFromFavorites(yoyoMovieOverview)
        }
    }
}
