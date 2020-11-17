package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.feature.movie.MovieSortModel
import com.alperb.yoyocinema.feature.movie.MovieSorter
import com.alperb.yoyocinema.model.YoyoMovieOverview
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SortMovieListUseCase @Inject constructor(
    val movieSorter: MovieSorter,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun sortMovieList(
        movieList: List<YoyoMovieOverview>,
        sortModel: MovieSortModel
    ): UIState<List<YoyoMovieOverview>> {
        return withContext(dispatcherProvider.default) {
            val sortedList = movieSorter.sort(movieList, sortModel.sortingOption, sortModel.isReverse)
            UIState.Success(sortedList)
        }
    }
}
