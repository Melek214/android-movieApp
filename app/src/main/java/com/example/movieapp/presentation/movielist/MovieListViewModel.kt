package com.example.movieapp.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        loadPopularMovies(apiKey = "7541c175779f8bb63c021aacf59fc919")
    }

    fun loadPopularMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _movies.value = getPopularMoviesUseCase(apiKey)
            } catch (e: Exception) {
                // Hata yönetimi (log, UI mesaj vs.)
                e.printStackTrace()
            }
        }
    }
}