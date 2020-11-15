package com.alperb.yoyocinema.feature.movie

import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.common.recycler.RecyclerComponentPresentation
import com.alperb.yoyocinema.core.common.recycler.RecyclerModelWrapper
import com.alperb.yoyocinema.model.YoyoMovieOverview

class MovieItemPresentation(
    val movieOverview: YoyoMovieOverview
) : RecyclerComponentPresentation()

class MovieItemPresentationWrapper(
    override val presentation: RecyclerComponentPresentation
) : RecyclerModelWrapper {
    override fun getLayoutId() = R.layout.item_movie_overview
}
