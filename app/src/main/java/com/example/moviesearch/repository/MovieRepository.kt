
package com.example.moviesearch.repository

import android.util.Log
import com.example.moviesearch.appdata.MovieResponse
import com.example.moviesearch.network.Api

class MovieRepository(private val api: Api) {
    suspend fun getDiscoverMovies(apiKey: String): Result<MovieResponse> {
        return try {
            Log.d("MovieRepository", "Fetching movies from API...")
            val response = api.getDiscoverMovies(apiKey) // Pass the API key here
            Log.d("MovieRepository", "Movies fetched successfully: ${response.results.size} results")
            Result.Success(response)
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching movies: ${e.message}")
            Result.Error(e)
        }
    }
}
sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}