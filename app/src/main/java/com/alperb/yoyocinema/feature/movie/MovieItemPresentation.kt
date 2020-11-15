package com.alperb.yoyocinema.feature.movie

import com.alperb.yoyocinema.R
import com.alperb.yoyocinema.core.common.recycler.RecyclerComponentPresentation
import com.alperb.yoyocinema.core.common.recycler.RecyclerModelWrapper

class MovieItemPresentation(
    val title: String
) : RecyclerComponentPresentation() {

}

class MovieItemPresentationWrapper(
    override val presentation: RecyclerComponentPresentation
) : RecyclerModelWrapper {
    override fun getLayoutId() = R.layout.item_movie_overview
}
