package com.aprian1337.movie_catalogue.ui.main.movie

import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.models.MovieTv

class MoviesViewModel : ViewModel() {
    private var movies = emptyList<MovieTv>()
    private var moviesFeatured = emptyList<MovieTv>()

    fun setMovies(data : ArrayList<MovieTv>){
        movies = data.toMutableList()
    }

    fun setMoviesFeatured(data : ArrayList<MovieTv>){
        moviesFeatured = data.toMutableList()
    }

    fun getMovies() : List<MovieTv> = movies

    fun getMoviesFeatured() : List<MovieTv> = moviesFeatured
}