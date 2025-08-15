package com.example.movieapp.data.network.dto

data class MovieDto( //Burada API’den gelen JSON verilerini karşılayacak DTO sınıfları oluşturdum
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)