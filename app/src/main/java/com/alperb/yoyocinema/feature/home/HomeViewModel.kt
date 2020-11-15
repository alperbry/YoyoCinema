package com.alperb.yoyocinema.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alperb.yoyocinema.core.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    val currentPage = MutableLiveData<HomePageType>().apply {
        this.value = HomePageType.SEARCH
    }

    /*val currentPage: LiveData<HomePageType>
        get() = _currentPage*/

}
