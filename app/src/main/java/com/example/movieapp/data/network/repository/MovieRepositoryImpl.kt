package com.example.movieapp.data.network.repository

import com.example.movieapp.data.network.TmdbApi
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: TmdbApi
) : MovieRepository {

    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return api.getPopularMovies(apiKey).results.map { dto ->
            Movie(
                id = dto.id,
                title = dto.title,
                overview = dto.overview,
                posterPath = dto.poster_path,
                releaseDate = dto.release_date,
                voteAverage = dto.vote_average,

            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int, apiKey: String): Movie {
        val dto = api.getMovieDetails(movieId, apiKey)
        return Movie(
            id = dto.id,
            title = dto.title,
            overview = dto.overview,
            posterPath = dto.poster_path,
            releaseDate = dto.release_date,
            voteAverage = dto.vote_average,
        )
    }
}