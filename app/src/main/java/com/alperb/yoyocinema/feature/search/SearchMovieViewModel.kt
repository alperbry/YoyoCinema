package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.*
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.SingleLiveEvent
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.feature.movie.MovieItemPresentation
import com.alperb.yoyocinema.feature.movie.MovieItemPresentationWrapper
import com.alperb.yoyocinema.feature.movie.MovieSortModel
import com.alperb.yoyocinema.feature.movie.MovieSorter
import com.alperb.yoyocinema.model.YoyoMovieOverview
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ActivityScope
class SearchMovieViewModel @Inject constructor(
    searchMovieUseCase: SearchMovieUseCase,
    val sortMovieListUseCase: SortMovieListUseCase
) : BaseViewModel() {

    val queriedMovie: SingleLiveEvent<String> = SingleLiveEvent()

    val checkedSortingRadioButton: MutableLiveData<Int> = MutableLiveData(R.id.radioButtonSortPoint)

    private val selectedSortingOption: LiveData<MovieSortModel> =
        Transformations.map(checkedSortingRadioButton) { id ->
            return@map when (id) {
                R.id.radioButtonSortPoint -> MovieSortModel(MovieSorter.SortingOption.POINT)
                R.id.radioButtonSortName -> MovieSortModel(MovieSorter.SortingOption.NAME)
                else -> throw IllegalArgumentException("Invalid radio button.")
            }
        }

    private val movieListState: LiveData<UIState<List<YoyoMovieOverview>>> =
        Transformations.switchMap(queriedMovie) { query ->
            liveData {
                emit(UIState.Loading)
                emit(searchMovieUseCase.searchMovie(query, selectedSortingOption.value))
            }
        }

    private val sortedMovieListState: LiveData<UIState<List<YoyoMovieOverview>>> =
        Transformations.switchMap(selectedSortingOption) { sortOption ->
            liveData {
                if (movieListState.value is UIState.Success) {
                    emit(sortMovieListUseCase.sortMovieList(
                        (movieListState.value as UIState.Success<List<YoyoMovieOverview>>).data, sortOption)
                    )
                }
            }
        }

    private val movieListMediator : MediatorLiveData<UIState<List<YoyoMovieOverview>>> =
        MediatorLiveData<UIState<List<YoyoMovieOverview>>>()

    val movieListPresentationList =
        Transformations.map(movieListMediator) { result ->
            if (result is UIState.Success) {
                result.data.map {
                    MovieItemPresentationWrapper(MovieItemPresentation(it, ::onMovieItemClick))
                }
            } else {
                null
            }
        }

    val navigate = SingleLiveEvent<Int>()

    override val loadingObservableList: List<LiveData<*>> = listOf(movieListState)

    init {
        movieListMediator.addSource(movieListState) {
            movieListMediator.value = it
        }
        movieListMediator.addSource(sortedMovieListState) {
            movieListMediator.value = it
        }
    }

    fun onMovieItemClick(id: Int) {
        navigate.value = id
    }

}
