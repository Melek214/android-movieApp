package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie

interface MovieRepository {  //Burada repository interface’ini tanımlarız.  UI katmanı yalnızca bu interface ile konuşur, API detaylarını bilmez.
    suspend fun getPopularMovies(apiKey: String): List<Movie>
    suspend fun getMovieDetails(movieId: Int, apiKey: String): Movie
}
