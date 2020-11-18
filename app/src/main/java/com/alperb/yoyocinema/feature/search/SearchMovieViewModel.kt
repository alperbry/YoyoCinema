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
import com.alperb.yoyocinema.feature.movie.usecase.SearchMovieUseCase
import com.alperb.yoyocinema.feature.movie.usecase.SortMovieListUseCase
import com.alperb.yoyocinema.model.YoyoMovieOverview
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ActivityScope
class SearchMovieViewModel @Inject constructor(
    searchMovieUseCase: SearchMovieUseCase,
    val sortMovieListUseCase: SortMovieListUseCase
) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String> = MutableLiveData()

    val checkedSortingRadioButton: MutableLiveData<Int> = MutableLiveData(R.id.radioButtonSortPoint)

    private val selectedSortingOption: LiveData<MovieSortModel> =
        Transformations.map(checkedSortingRadioButton) { id ->
            return@map when (id) {
                R.id.radioButtonSortPoint -> MovieSortModel(MovieSorter.SortingOption.POINT)
                R.id.radioButtonSortName -> MovieSortModel(MovieSorter.SortingOption.NAME, isReverse = false)
                else -> throw IllegalArgumentException("Invalid radio button.")
            }
        }

    val movieListState: LiveData<UIState<List<YoyoMovieOverview>>> =
        Transformations.switchMap(queriedMovie) { query ->
            liveData {
                emit(UIState.Loading)
                emit(searchMovieUseCase.searchMovie(query, selectedSortingOption.value))
            }
        }

    private val _movieList = Transformations.map(movieListState) {
        return@map if (it is UIState.Success) {
            it.data
        } else {
            null
        }
    }


    private val sortedMovieListState: LiveData<UIState<List<YoyoMovieOverview>>> =
        Transformations.switchMap(selectedSortingOption) { sortOption ->
            liveData {
                if (movieListState.value is UIState.Success) {
                    _movieList.value?.let { movieList ->
                        emit(sortMovieListUseCase.sortMovieList(
                            movieList, sortOption)
                        )
                    }
                }
            }
        }

    private val _sortedMovieList =
        Transformations.map(sortedMovieListState) {
            return@map if (it is UIState.Success) {
                it.data
            } else {
                null
            }
        }

    /**
     * Added in need of emitting from multiple sources, both network request and
     * sorting strategy changes.
     */
    private val movieListMediator : MediatorLiveData<List<YoyoMovieOverview>?> =
        MediatorLiveData<List<YoyoMovieOverview>?>()

    val movieListPresentationList =
        Transformations.map(movieListMediator) { result ->
            result?.map {
                MovieItemPresentationWrapper(MovieItemPresentation(it, ::onMovieItemClick))
            }
        }

    val navigate = SingleLiveEvent<Int>()

    init {
        movieListMediator.addSource(_movieList) {
            movieListMediator.value = it
        }
        movieListMediator.addSource(_sortedMovieList) {
            movieListMediator.value = it
        }
    }

    fun onMovieItemClick(id: Int) {
        navigate.value = id
    }

}
