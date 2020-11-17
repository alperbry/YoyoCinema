package com.alperb.yoyocinema.feature.detail

import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.core.common.util.DisplayHelper
import com.alperb.yoyocinema.feature.favorite.CheckFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.favorite.UpdateFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.movie.CastItemPresentation
import com.alperb.yoyocinema.feature.movie.CastItemPresentationWrapper
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val GENRE_SEPERATOR = ", "
private const val CAST_ITEM_SPACING = 12

class MovieDetailViewModel @Inject constructor(
    val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
    val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase
) : BaseViewModel() {

    private val _movieDetail: MutableLiveData<YoyoMovieDetail> = MutableLiveData()

    val movieDetail: LiveData<YoyoMovieDetail>
        get() = _movieDetail

    val castItemList = Transformations.map(movieDetail) { movieDetail ->
        movieDetail.castList?.map {
            CastItemPresentationWrapper(CastItemPresentation(it))
        }
    }

    val initialFavoriteStatus = MutableLiveData(false)

    private var isCheckedAsFavorite = false

    val genreText = Transformations.map(movieDetail) {
        it.genres.fold("") { acc, genre ->
            val seperator = if (acc.isEmpty().not()) GENRE_SEPERATOR else acc
            acc + seperator + genre.name
        }
    }

    val castItemSpacing = CAST_ITEM_SPACING

    fun initialize(movieDetail: YoyoMovieDetail?) {
        movieDetail?.let {
            _movieDetail.value = it
            setInitialFavoriteStatus(it)
        }
    }

    fun onScreenClosed() {
        updateFavoriteStatusPersistently()
    }

    fun onFavoriteToggleChecked(button: CompoundButton, checked: Boolean) {
        isCheckedAsFavorite = checked
    }

    private fun setInitialFavoriteStatus(movieDetail: YoyoMovieDetail) {
        viewModelScope.launch {
            val state = checkFavoriteMovieUseCase.checkFavoriteMovie(movieDetail)
            if (state is UIState.Success) {
                initialFavoriteStatus.value = state.data
                isCheckedAsFavorite = state.data
            }
        }
    }

    private fun updateFavoriteStatusPersistently() {
        //if (isCheckedAsFavorite == initialFavoriteStatus.value) return
        viewModelScope.launch {
            movieDetail.value?.let {
                updateFavoriteMovieUseCase.updateFavoriteMovie(
                    YoyoMovieOverview(it),
                    isCheckedAsFavorite
                )
            }
        }
    }

}
