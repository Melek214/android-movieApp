package com.example.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val runtime: Int?, // süre dakika cinsinden (ör: 115)
    val genres: List<String>? // kategoriler
)

data class Genre(
    val id: Int,
    val name: String
)

