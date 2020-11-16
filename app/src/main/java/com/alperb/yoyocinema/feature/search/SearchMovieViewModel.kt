package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.*
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.feature.movie.MovieItemPresentation
import com.alperb.yoyocinema.feature.movie.MovieItemPresentationWrapper
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(useCase: SearchMovieUseCase) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String?> = MutableLiveData()

    val movieList: LiveData<List<MovieItemPresentationWrapper>?> = Transformations.switchMap(queriedMovie) { query ->
        liveData {
            //emit(UIState.Loading) to be added
            if (query == null) {
                emit(null)
                return@liveData
            }
            val result = useCase.searchMovie(query)
            if (result is UIState.Success) {
                val list = result.data.map {
                    MovieItemPresentationWrapper(MovieItemPresentation(it))}
                emit(list)
            } else {
                null
            }
        }
    }

}
