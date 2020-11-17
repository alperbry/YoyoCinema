package com.alperb.yoyocinema.feature.movie

data class MovieSortModel(
    val sortingOption: MovieSorter.SortingOption = MovieSorter.SortingOption.POINT,
    val isReverse: Boolean = true
)
