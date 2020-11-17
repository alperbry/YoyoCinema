package com.alperb.yoyocinema.feature.movie

import com.alperb.yoyocinema.core.common.extensions.orZero
import com.alperb.yoyocinema.di.ActivityScope
import com.alperb.yoyocinema.model.YoyoMovieOverview
import javax.inject.Inject

@ActivityScope
class MovieSorter @Inject constructor() {
    fun sort(
        movieDetailList: List<YoyoMovieOverview>,
        sortingOption: SortingOption,
        reverse: Boolean = false
    ): List<YoyoMovieOverview> {
        return when (sortingOption) {
            SortingOption.NAME -> sortByName(movieDetailList, reverse)
            SortingOption.POINT -> sortByPoint(movieDetailList, reverse)
            else -> movieDetailList
        }
    }

    private fun sortByName(movieDetailList: List<YoyoMovieOverview>, reverse: Boolean = false): List<YoyoMovieOverview> {
        return sort(movieDetailList, reverse) { yoyoMovieOverview ->
            yoyoMovieOverview.title
        }
    }

    private fun sortByPoint(movieDetailList: List<YoyoMovieOverview>, reverse: Boolean = false): List<YoyoMovieOverview> {
        return sort(movieDetailList, reverse) { yoyoMovieOverview ->
            yoyoMovieOverview.voteAverage.orZero()
        }
    }

    private fun <T : Comparable<T>, V> sort(
        movieDetailList: List<V>,
        reverse: Boolean,
        block: (V) -> T
    ): List<V> {
        return if (reverse) {
            movieDetailList.sortedByDescending { block.invoke(it) }
        } else {
            movieDetailList.sortedBy { block.invoke(it) }
        }
    }

    enum class SortingOption {
        NAME,
        POINT,
        NONE
    }
}
