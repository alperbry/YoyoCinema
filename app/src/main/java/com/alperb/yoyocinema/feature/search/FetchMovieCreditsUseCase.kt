package com.alperb.yoyocinema.feature.search

import com.alperb.yoyocinema.core.BaseUseCase
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.model.YoyoCast
import com.alperb.yoyocinema.network.repository.MoviesRepository
import javax.inject.Inject

class FetchMovieCreditsUseCase @Inject constructor(
    val repository: MoviesRepository
): BaseUseCase() {

    suspend fun fetchMovieCredits(movieId: Int): UIState<List<YoyoCast>> {
        return handleResponse(repository.fetchMovieCredits(movieId)) { credits ->
            credits?.castList?.mapNotNull { cast ->
                cast?.let {
                    YoyoCast.newInstance(it)
                }
            }.orEmpty()
        }
    }
}
