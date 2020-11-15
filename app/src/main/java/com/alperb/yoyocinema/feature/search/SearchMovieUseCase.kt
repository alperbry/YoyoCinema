package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.network.Response
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.repository.MoviesRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(val repository: MoviesRepository) : BaseUseCase() {

    //fixme divide into small functions
    suspend fun searchMovie(query: String): UIState<List<YoyoMovieOverview>> {
        val movieList = repository.searchMovie(query)
        return handleResponse(movieList) { movieOverviewList ->
            movieOverviewList?.mapNotNull { movieOverview ->
                movieOverview?.let {
                    YoyoMovieOverview.getInstance(it)
                }
            }.orEmpty()
        }
    }

}
