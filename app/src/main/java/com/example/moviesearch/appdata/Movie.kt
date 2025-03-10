package com.example.moviesearch.appdata

data class MovieResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String?,
    val name: String?,
    val overview: String,
    val poster_path: String?,
    val release_date: String?
)
