package com.alperb.yoyocinema.core.network

import com.alperb.yoyocinema.network.model.MovieCredits
import com.alperb.yoyocinema.network.model.MovieDetail
import com.alperb.yoyocinema.network.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchForMovies(
        @Query("query") query: String,
        @Query("language") language: String
    ): MovieSearchResponse?

    @GET("movie")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieDetail?

    @GET("movie/{movie_id}/credits")
    suspend fun fetchCreditsOfMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieCredits?
}
