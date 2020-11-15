package com.alperb.yoyocinema.feature.home

import com.alperb.yoyocinema.R

enum class HomePageType(val index: Int, val id: Int) {
    SEARCH(0, R.id.action_search),
    FAVORITE(1, R.id.action_favorites);

    companion object {
        fun getById(id: Int): HomePageType? {
            return values().firstOrNull { id == it.id }
        }
    }
}
