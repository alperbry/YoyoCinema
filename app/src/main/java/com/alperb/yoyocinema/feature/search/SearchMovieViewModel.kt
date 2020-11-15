package com.alperb.yoyocinema.feature.search

import androidx.lifecycle.MutableLiveData
import com.alperb.yoyocinema.core.BaseViewModel
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(useCase: SearchMovieUseCase) : BaseViewModel() {

    val queriedMovie: MutableLiveData<String?> = MutableLiveData()
}
