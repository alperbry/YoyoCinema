package com.alperb.yoyocinema.feature.detail

import android.widget.CompoundButton
import androidx.lifecycle.*
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.feature.favorite.CheckFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.favorite.UpdateFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.movie.CastItemPresentation
import com.alperb.yoyocinema.feature.movie.CastItemPresentationWrapper
import com.alperb.yoyocinema.feature.search.FetchMovieDetailsUseCase
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.model.Genre
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val GENRE_SEPERATOR = ", "
private const val CAST_ITEM_SPACING = 12

class MovieDetailViewModel @Inject constructor(
    val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
    val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase
) : BaseViewModel() {

    private val _movieId: MutableLiveData<Int> = MutableLiveData()

    private val _movieDetailStateOwner: LiveData<UIState<YoyoMovieDetail?>> =
        Transformations.switchMap(_movieId) { id ->
            liveData {
                emit(UIState.Loading)
                emit(fetchMovieDetailsUseCase.fetchMovieDetails(id))
            }
        }

    val movieDetail =
        Transformations.map(_movieDetailStateOwner) { movieDetailState ->
            return@map if (movieDetailState is UIState.Success) {
                movieDetailState.data
            } else null
        }

    val castItemList =
        Transformations.map(movieDetail) { movieDetail ->
            movieDetail?.castList?.map {
                CastItemPresentationWrapper(CastItemPresentation(it))
            }
        }

    val initialFavoriteStatus = MutableLiveData(false)

    private var isCheckedAsFavorite = false

    val genreText = Transformations.map(movieDetail) { movieDetail ->
       generateGenreText(movieDetail?.genres)
    }

    val castItemSpacing = CAST_ITEM_SPACING

    override val loadingObservableList = listOf(_movieDetailStateOwner)

    fun initialize(movieId: Int?) {
        movieId?.let {
            _movieId.value = it
            setInitialFavoriteStatus(it)
        }
    }

    fun onScreenClosed() {
        updateFavoriteStatusPersistently()
    }

    fun onFavoriteToggleChecked(button: CompoundButton, checked: Boolean) {
        isCheckedAsFavorite = checked
    }

    private fun setInitialFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            val state = checkFavoriteMovieUseCase.checkFavoriteMovie(movieId)
            if (state is UIState.Success) {
                initialFavoriteStatus.value = state.data
                isCheckedAsFavorite = state.data
            }
        }
    }

    //fixme
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

    private fun generateGenreText(genreList: List<Genre>?): String? {
        return genreList?.fold("") { acc, genre ->
            val seperator = if (acc.isEmpty().not()) GENRE_SEPERATOR else acc
            acc + seperator + genre.name
        }
    }

}
