package com.alperb.yoyocinema.di

import com.alperb.yoyocinema.feature.detail.MovieDetailFragment
import com.alperb.yoyocinema.feature.favorite.FavoriteMoviesFragment
import com.alperb.yoyocinema.feature.home.HomeHostActivity
import com.alperb.yoyocinema.feature.home.HomeFragment
import com.alperb.yoyocinema.feature.search.SearchMovieFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [HomeViewModelsModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeHostActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: SearchMovieFragment)

    fun inject(fragment: FavoriteMoviesFragment)

    fun inject(fragment: MovieDetailFragment)

}
