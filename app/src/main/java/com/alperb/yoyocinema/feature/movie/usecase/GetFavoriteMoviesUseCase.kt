package com.alperb.yoyocinema.feature.movie.usecase

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.db.FavoriteMoviesRepository
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    val repository: FavoriteMoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun getMovies(): UIState<List<YoyoMovieOverview>> {
        return handleResponse(repository.getFavoriteMoviesList()) { movieEntityList ->
            movieEntityList?.map {
                YoyoMovieOverview(it)
            }.orEmpty()
        }
    }
}
