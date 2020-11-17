package com.alperb.yoyocinema.db

import com.alperb.yoyocinema.core.BaseRepository
import com.alperb.yoyocinema.core.coroutines.DispatcherProvider
import com.alperb.yoyocinema.core.network.FailureHandler
import com.alperb.yoyocinema.core.network.Response
import com.alperb.yoyocinema.core.network.SuccessHandler
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.model.MovieOverviewEntity
import javax.inject.Inject

@ActivityScope
class FavoriteMoviesRepository @Inject constructor(
    override val successHandler: SuccessHandler,
    override val failureHandler: FailureHandler,
    val moviesDao: MoviesDatabaseDao,
    dispatcherProvider: DispatcherProvider
) : BaseRepository(dispatcherProvider) {

    suspend fun setMovieAsFavorite(movie: MovieOverviewEntity): Response<Long?> {
        return apiCall {
            moviesDao.insert(movie)
        }
    }

    suspend fun removeMovieFromFavorites(movie: MovieOverviewEntity): Response<Int?> {
        return apiCall {
            moviesDao.delete(movie)
        }
    }

    suspend fun getFavoriteMovie(id: Int): Response<MovieOverviewEntity?> {
        return apiCall {
            moviesDao.get(id)
        }
    }

    suspend fun getFavoriteMoviesList(): Response<List<MovieOverviewEntity>?> {
        return apiCall {
            moviesDao.getAll()
        }
    }
}
