package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.feature.movie.MovieSortModel
import com.alperb.yoyocinema.feature.movie.MovieSorter
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.model.MovieOverview
import com.alperb.yoyocinema.network.repository.MoviesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MIN_QUERY_LENGTH = 2

class SearchMovieUseCase @Inject constructor(
    val repository: MoviesRepository,
    val movieSorter: MovieSorter,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun searchMovie(query: String?, movieSortModel: MovieSortModel?): UIState<List<YoyoMovieOverview>> {
        return withContext(dispatcherProvider.default) {
            if (validateQuery(query).not()) {
                return@withContext UIState.Success(emptyList())
            }
            val movieList = repository.searchMovie(query!!)
            handleResponse(movieList) { movieOverviewList ->
                val movieModelList = mapToUIModel(movieOverviewList)
                movieSortModel?.let {
                    movieSorter.sort(movieModelList, movieSortModel.sortingOption, movieSortModel.isReverse)
                } ?: movieModelList
            }
        }
    }

    private fun validateQuery(query: String?): Boolean {
        return (query != null) && (query.length >= MIN_QUERY_LENGTH)
    }

    private fun mapToUIModel(movieOverviewList: List<MovieOverview?>?): List<YoyoMovieOverview> {
        return movieOverviewList?.mapNotNull { movieOverview ->
            movieOverview?.let {
                YoyoMovieOverview.getInstance(it)
            }
        }.orEmpty()
    }

}
