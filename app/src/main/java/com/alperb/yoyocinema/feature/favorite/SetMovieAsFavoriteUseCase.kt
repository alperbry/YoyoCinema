package com.alperb.yoyocinema.feature.favorite

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.db.FavoriteMoviesRepository
import com.alperb.yoyocinema.db.MoviesDatabaseDao
import com.alperb.yoyocinema.feature.movie.MovieSorter
import com.alperb.yoyocinema.model.MovieOverviewEntity
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.repository.MoviesRepository
import javax.inject.Inject

class SetMovieAsFavoriteUseCase@Inject constructor(
    val repository: FavoriteMoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun setMovieAsFavorite(yoyoMovieOverview: YoyoMovieOverview) {
        repository.setMovieAsFavorite(MovieOverviewEntity(yoyoMovieOverview))
    }
}
