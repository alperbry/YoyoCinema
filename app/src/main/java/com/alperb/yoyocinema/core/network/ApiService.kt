package com.alperb.yoyocinema.core.network

import com.alperb.yoyocinema.network.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchForMovies(
        @Query("query") query: String,
        @Query("language") language: String
    ): MovieSearchResponse?
}
