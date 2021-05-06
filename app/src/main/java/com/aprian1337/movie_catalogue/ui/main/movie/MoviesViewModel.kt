package com.aprian1337.movie_catalogue.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.MoviesResponse
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.data.repository.MainRepository

class MoviesViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var movies = emptyList<MovieTv>()
    private var moviesFeatured = emptyList<MovieTv>()

    fun setMovies(data : List<MovieTv>){
        movies = data
    }

    fun getFeaturedMovies(): LiveData<List<MovieTv>> = mainRepository.getFeaturedMovies()

    fun getNowMovies(): LiveData<List<MovieTv>> = mainRepository.getNowMovies()

    fun getGenreMovies() : LiveData<List<GenresItem>> = mainRepository.getGenreMovies()

    fun setMoviesFeatured(data : ArrayList<MovieTv>){
        moviesFeatured = data.toMutableList()
    }

    fun getMovies() : List<MovieTv> = movies

    fun getMoviesFeatured() : List<MovieTv> = moviesFeatured
}