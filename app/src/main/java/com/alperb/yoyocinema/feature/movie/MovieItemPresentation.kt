package com.alperb.yoyocinema.feature.movie

import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.common.recycler.RecyclerComponentPresentation
import com.alperb.yoyocinema.core.common.recycler.RecyclerModelWrapper
import com.alperb.yoyocinema.model.YoyoMovieOverview

private const val DATE_SPLIT_CHAR = '-'
class MovieItemPresentation(
    val movieOverview: YoyoMovieOverview,
    private val onClickAction: (Int) -> Unit
) : RecyclerComponentPresentation() {

    val releaseYear = movieOverview.releaseDate?.split(DATE_SPLIT_CHAR)?.firstOrNull()

    fun onClick() {
        onClickAction.invoke(movieOverview.id)
    }
}

class MovieItemPresentationWrapper(
    override val presentation: RecyclerComponentPresentation
) : RecyclerModelWrapper {
    override fun getLayoutId() = R.layout.item_movie_overview
}
