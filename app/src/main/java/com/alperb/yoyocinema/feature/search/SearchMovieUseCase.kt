package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.repository.MoviesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    val repository: MoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    //fixme divide into small functions
    suspend fun searchMovie(query: String): UIState<List<YoyoMovieOverview>> {
        return withContext(dispatcherProvider.default) {
            val movieList = repository.searchMovie(query)
            handleResponse(movieList) { movieOverviewList ->
                movieOverviewList?.mapNotNull { movieOverview ->
                    movieOverview?.let {
                        YoyoMovieOverview.getInstance(it)
                    }
                }.orEmpty()
            }
        }
    }

}
