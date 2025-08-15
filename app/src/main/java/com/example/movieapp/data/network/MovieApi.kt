package com.example.movieapp.data.network

import com.example.movieapp.data.network.dto.MovieDto
import com.example.movieapp.data.network.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")

    suspend fun getPopularMovies(
        @Query("language") language: String = "tr-TR",
        @Query("page") page: Int = 1
    ): MovieListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "tr-TR"
    ): MovieDto
}
