package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.*
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.feature.movie.MovieItemPresentation
import com.alperb.yoyocinema.feature.movie.MovieItemPresentationWrapper
import com.alperb.yoyocinema.feature.movie.MovieSortModel
import com.alperb.yoyocinema.feature.movie.MovieSorter
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

@ActivityScope
class SearchMovieViewModel @Inject constructor(
    searchMovieUseCase: SearchMovieUseCase,
    val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    val sortMovieListUseCase: SortMovieListUseCase
) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String?> = MutableLiveData()

    private val selectedMovieId: MutableLiveData<Int> = MutableLiveData()

    val checkedSortingRadioButton: MutableLiveData<Int> = MutableLiveData(R.id.radioButtonSortPoint)

    private val selectedSortingOption: LiveData<MovieSortModel> = Transformations.map(checkedSortingRadioButton) { id ->
        return@map when (id) {
            R.id.radioButtonSortPoint -> MovieSortModel(MovieSorter.SortingOption.POINT)
            R.id.radioButtonSortName -> MovieSortModel(MovieSorter.SortingOption.NAME)
            else -> MovieSortModel()
        }
    }

    private val movieListState: LiveData<UIState<List<YoyoMovieOverview>>> = Transformations.switchMap(queriedMovie) { query ->
        liveData {
            emit(UIState.Loading)
            emit(searchMovieUseCase.searchMovie(query.orEmpty(), selectedSortingOption.value))
        }
    }

    private val sortedMovieListState: LiveData<UIState<List<YoyoMovieOverview>>> = Transformations.switchMap(selectedSortingOption) { sortOption ->
        liveData {
            if (movieListState.value is UIState.Success) {
                emit(sortMovieListUseCase.sortMovieList(
                    (movieListState.value as UIState.Success<List<YoyoMovieOverview>>).data, sortOption)
                )
            }
        }
    }

    private val movieListMediator : MediatorLiveData<UIState<List<YoyoMovieOverview>>> = MediatorLiveData<UIState<List<YoyoMovieOverview>>>()

    val movieListPresentationList = Transformations.map(movieListMediator) { result ->
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

    init {
        movieListMediator.addSource(movieListState) {
            movieListMediator.value =  it
        }
        movieListMediator.addSource(sortedMovieListState) {
            movieListMediator.value = it
        }
    }

    fun onMovieItemClick(id: Int) {
        selectedMovieId.value = id
    }

}
