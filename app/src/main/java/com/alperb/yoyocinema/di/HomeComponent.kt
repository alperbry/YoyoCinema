package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.feature.favorite.FavoriteMoviesFragment
import com.alperb.yoyocinema.feature.home.HomeActivity
import com.alperb.yoyocinema.feature.search.SearchMovieFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)

    fun inject(fragment: SearchMovieFragment)

    fun inject(fragment: FavoriteMoviesFragment)

}
