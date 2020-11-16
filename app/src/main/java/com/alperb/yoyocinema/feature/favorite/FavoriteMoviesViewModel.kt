package com.alperb.yoyocinema.feature.favorite

import androidx.lifecycle.MutableLiveData
import com.alperb.yoyocinema.core.BaseViewModel
import com.alperb.yoyocinema.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class FavoriteMoviesViewModel @Inject constructor(getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase) : BaseViewModel() {

    val text = MutableLiveData<String>("favorite movies")
}