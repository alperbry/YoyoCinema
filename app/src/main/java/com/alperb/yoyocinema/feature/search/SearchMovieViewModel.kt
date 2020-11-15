package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.*
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(useCase: SearchMovieUseCase) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String?> = MutableLiveData()

    val movieNameToShow: LiveData<String> = Transformations.switchMap(queriedMovie) { query ->
        liveData {
            //emit(UIState.Loading) to be added
            if (query == null) {
                emit(null)
                return@liveData
            }
            val result = useCase.searchMovie(query)
            if (result is UIState.Success) {
                emit(result.data.firstOrNull()?.title)
            } else {
                null
            }
        }

    }

}
