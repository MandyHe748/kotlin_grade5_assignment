package com.example.moviesearch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearch.appdata.Movie
import com.example.moviesearch.repository.MovieRepository
import com.example.moviesearch.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchDiscoverMovies(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("MovieViewModel", "Fetching movies...")
            when (val result = repository.getDiscoverMovies(apiKey)) {
                is Result.Success -> {
                    Log.d("MovieViewModel", "Movies fetched: ${result.data.results.size}")
                    _movies.value = result.data.results
                }
                is Result.Error -> {
                    Log.e("MovieViewModel", "Error fetching movies: ${result.exception.message}")
                    _error.value = result.exception.message
                }
            }
            _isLoading.value = false
        }
    }
}
class MovieViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

