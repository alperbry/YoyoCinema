package com.alperb.yoyocinema.feature.movie.usecase

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.core.network.Response
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.network.model.MovieCredits
import com.alperb.yoyocinema.network.model.MovieDetail
import com.alperb.yoyocinema.network.repository.MoviesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    val repository: MoviesRepository,
    dispatcherProvider: DispatcherProvider
) : BaseUseCase(dispatcherProvider) {

    suspend fun fetchMovieDetails(movieId: Int): UIState<YoyoMovieDetail?> {
        return withContext(dispatcherProvider.default) {
            val movieDetail = async { repository.fetchMovieDetails(movieId) }
            val castList = async { repository.fetchMovieCredits(movieId) }

            generateMovieDetail(movieDetail.await(), castList.await())
        }
    }

    private suspend fun generateMovieDetail(
        movieDetail: Response<MovieDetail?>,
        castList: Response<MovieCredits?>
    ): UIState<YoyoMovieDetail?> {
        val movieState = handleResponse(movieDetail) {it}
        if (movieState is UIState.Failure) {
            return movieState
        }

        val castList = handleResponse(castList) { it?.castList }
        if (castList is UIState.Failure) {
            return castList
        }

        movieState as UIState.Success
        castList as UIState.Success

        return movieState.data?.let {
            UIState.Success(YoyoMovieDetail(it, castList.data))
        } ?: UIState.Success(null)
    }

}
