package com.alperb.yoyocinema.network.repository

import com.alperb.yoyocinema.core.BaseRepository
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.core.network.ApiService
import com.alperb.yoyocinema.core.network.FailureHandler
import com.alperb.yoyocinema.core.network.Response
import com.alperb.yoyocinema.core.network.SuccessHandler
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.network.model.MovieOverview
import javax.inject.Inject

@ActivityScope
class MoviesRepository @Inject constructor(
    override val successHandler: SuccessHandler,
    override val failureHandler: FailureHandler,
    val apiService: ApiService,
    dispatcherProvider: DispatcherProvider
) : BaseRepository(dispatcherProvider) {

    suspend fun searchMovie(query: String): Response<List<MovieOverview?>?> {
        return apiCall {
            apiService.searchForMovies(query, language = "en")?.results //fixme
        }
    }
}
