package com.alperb.yoyocinema.di

import androidx.lifecycle.ViewModel
import com.alperb.yoyocinema.feature.detail.MovieDetailViewModel
import com.alperb.yoyocinema.feature.favorite.FavoriteMoviesViewModel
import com.alperb.yoyocinema.feature.home.HomeViewModel
import com.alperb.yoyocinema.feature.search.SearchMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieViewModel::class)
    abstract fun bindSearchMovieViewModel(searchMovieViewModel: SearchMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    abstract fun bindFavoriteMoviesViewModel(favoriteMoviesViewModel: FavoriteMoviesViewModel): ViewModel
}
