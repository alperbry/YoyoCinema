package com.alperb.yoyocinema.feature.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alperb.yoyocinema.feature.favorite.FavoriteMoviesFragment
import com.alperb.yoyocinema.feature.search.SearchMovieFragment
import java.lang.IllegalArgumentException

private const val HOME_PAGE_ITEM_COUNT = 2

class HomePagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = HOME_PAGE_ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            HomePageType.SEARCH.index -> SearchMovieFragment.newInstance()
            HomePageType.FAVORITE.index -> FavoriteMoviesFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid position id.")
        }
    }

}
