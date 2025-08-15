package com.example.movieapp.data.network

import com.example.movieapp.data.network.dto.MovieDto
import com.example.movieapp.data.network.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi { // Retrofit ile API endpoint'leri burada tanımlıyorum

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDto
}