package com.alperb.yoyocinema.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.SingleLiveEvent
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.feature.movie.MovieItemPresentation
import com.alperb.yoyocinema.feature.movie.MovieItemPresentationWrapper
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

@ActivityScope
class FavoriteMoviesViewModel @Inject constructor(
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel() {

    private val _initialize: SingleLiveEvent<Any> = SingleLiveEvent()

    private val _movieListState: LiveData<UIState<List<YoyoMovieOverview>>> =
        Transformations.switchMap(_initialize) {
            liveData {
                emit(UIState.Loading)
                emit(getFavoriteMoviesUseCase.getMovies())
            }
        }

    private val _moviesList = Transformations.map(_movieListState) {
        return@map if (it is UIState.Success) {
            it.data
        } else {
            null
        }
    }

    val movieGridPresentationList =
        Transformations.map(_moviesList) { movieItemList ->
            if (movieItemList == null) return@map null

            movieItemList.map {
                MovieItemPresentationWrapper(MovieItemPresentation(it, ::onMovieItemClick))
            }
        }

    val backgroundImageUrl =
        Transformations.map(_moviesList) { movieItemList ->
            movieItemList?.first()?.posterPath
        }

    val navigate: SingleLiveEvent<Int> = SingleLiveEvent()

    override val loadingObservableList = listOf(_movieListState)

    fun onMovieItemClick(id: Int) {
        navigate.value = id
    }

    /**
     * It is needed to provide consistency since the favorite list
     * could be changed anytime.
     */
    fun onAppearOnScreen() {
        _initialize.call()
    }

}
