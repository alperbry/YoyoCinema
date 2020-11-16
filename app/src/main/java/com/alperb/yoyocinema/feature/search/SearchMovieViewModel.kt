package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.*
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.feature.movie.MovieItemPresentation
import com.alperb.yoyocinema.feature.movie.MovieItemPresentationWrapper
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

@ActivityScope
class SearchMovieViewModel @Inject constructor(
    searchMovieUseCase: SearchMovieUseCase,
    val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String?> = MutableLiveData()

    val selectedMovieId: MutableLiveData<Int> = MutableLiveData()

    val movieListState: LiveData<UIState<List<YoyoMovieOverview>>> = Transformations.switchMap(queriedMovie) { query ->
        liveData {
            emit(UIState.Loading)
            emit(searchMovieUseCase.searchMovie(query.orEmpty()))
        }
    }

    val movieListPresentationList = Transformations.map(movieListState) { result ->
        if (result is UIState.Success) {
            result.data.map {
                MovieItemPresentationWrapper(MovieItemPresentation(it, ::onMovieItemClick))
            }
        } else {
            null
        }
    }

    val movieDetailState: LiveData<UIState<YoyoMovieDetail?>> = Transformations.switchMap(selectedMovieId) { id ->
        liveData {
            emit(UIState.Loading)
            emit(fetchMovieDetailsUseCase.fetchMovieDetails(id))
        }
    }

    override val loadingObservableList: List<LiveData<*>> = listOf(movieListState, movieDetailState)

    fun onMovieItemClick(id: Int) {
        selectedMovieId.value = id
    }

}
