package com.alperb.yoyocinema.feature.detail

import androidx.lifecycle.*
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.core.common.ToolbarModel
import com.alperb.yoyocinema.core.common.UIState
import com.alperb.yoyocinema.feature.favorite.CheckFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.favorite.UpdateFavoriteMovieUseCase
import com.alperb.yoyocinema.feature.movie.CastItemPresentation
import com.alperb.yoyocinema.feature.movie.CastItemPresentationWrapper
import com.alperb.yoyocinema.feature.search.FetchMovieDetailsUseCase
import com.alperb.yoyocinema.model.YoyoMovieDetail
import com.alperb.yoyocinema.model.YoyoMovieOverview
import com.alperb.yoyocinema.network.model.Genre
import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.common.SingleLiveEvent
import com.alperb.yoyocinema.core.common.error.ErrorModel
import com.alperb.yoyocinema.core.common.extensions.orFalse
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

    val movieDetailStateOwner: LiveData<UIState<YoyoMovieDetail?>> =
        Transformations.switchMap(_movieId) { id ->
            liveData {
                emit(UIState.Loading)
                emit(fetchMovieDetailsUseCase.fetchMovieDetails(id))
            }
        }

    val movieDetail =
        Transformations.map(movieDetailStateOwner) { movieDetailState ->
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

    val errorModel =
        Transformations.map(movieDetailStateOwner) { state ->
            return@map if (state is UIState.Failure) {
                ErrorModel(state, ::onTryAgainClicked)
            } else {
                null
            }
        }

    val isMovieCheckedAsFavorite = MutableLiveData(false)

    private var initialFavorite: Boolean? = null

    val genreText = Transformations.map(movieDetail) { movieDetail ->
       generateGenreText(movieDetail?.genres)
    }

    val castItemSpacing = CAST_ITEM_SPACING

    override val toolbarModel = ToolbarModel(titleRes = R.string.title_movie_detail)

    override val loadingObservableList = listOf(movieDetailStateOwner)

    val onBottomEvent: SingleLiveEvent<Any> = SingleLiveEvent()

    fun initialize(movieId: Int?) {
        movieId?.let {
            _movieId.value = it
            setInitialFavoriteStatus(it)
        }
    }

    fun onScreenClosed() {
        updateFavoriteStatusPersistently()
    }

    fun onScrolledToBottom() {
        if (isMovieCheckedAsFavorite.value.orFalse().not()) {
            onBottomEvent.call()
        }
    }

    fun onTryAgainClicked() {
        _movieId.postValue(_movieId.value)
    }

    private fun setInitialFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            val state = checkFavoriteMovieUseCase.checkFavoriteMovie(movieId)
            if (state is UIState.Success) {
                isMovieCheckedAsFavorite.value = state.data
                initialFavorite = state.data
            }
        }
    }

    //fixme refactor
    private fun updateFavoriteStatusPersistently() {
        if (initialFavorite != null
            && isMovieCheckedAsFavorite.value == initialFavorite) {
            return
        }
        viewModelScope.launch {
            movieDetail.value?.let {
                updateFavoriteMovieUseCase.updateFavoriteMovie(
                    YoyoMovieOverview(it),
                    isMovieCheckedAsFavorite.value.orFalse()
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
