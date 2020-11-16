package com.alperb.yoyocinema.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.model.YoyoMovieDetail
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor() : BaseViewModel() {

    private val _movieDetail: MutableLiveData<YoyoMovieDetail> = MutableLiveData()

    val movieDetail: LiveData<YoyoMovieDetail>
        get() = _movieDetail

    fun initialize(movieDetail: YoyoMovieDetail?) {
        movieDetail?.let {
            _movieDetail.value = it
        }
    }
}
