package com.aprian1337.movie_catalogue.data.models

data class MovieTv(
    val id: Int,
    val title: String,
    val genre: List<Int>,
    val release: String,
    val overview: String,
    val image: String,
    val popularity: String
)