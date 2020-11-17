package com.alperb.yoyocinema.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.model.YoyoMovieDetail
import javax.inject.Inject

private const val GENRE_SEPERATOR = ", "

class MovieDetailViewModel @Inject constructor() : BaseViewModel() {

    private val _movieDetail: MutableLiveData<YoyoMovieDetail> = MutableLiveData()

    val movieDetail: LiveData<YoyoMovieDetail>
        get() = _movieDetail

    val genreText = Transformations.map(movieDetail) {
        it.genres.fold("") { acc, genre ->
            val seperator = if (acc.isEmpty().not()) GENRE_SEPERATOR else acc
            acc + seperator + genre.name
        }
    }

    fun initialize(movieDetail: YoyoMovieDetail?) {
        movieDetail?.let {
            _movieDetail.value = it
        }
    }
}
