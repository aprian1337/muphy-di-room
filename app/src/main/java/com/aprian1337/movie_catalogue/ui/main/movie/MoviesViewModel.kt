package com.aprian1337.movie_catalogue.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.data.repository.MainRepository

class MoviesViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getFeaturedMovies(): LiveData<List<MovieTv>> = mainRepository.getFeaturedMovies()

    fun getNowMovies(): LiveData<List<MovieTv>> = mainRepository.getNowMovies()

    fun getGenreMovies() : LiveData<List<GenresItem>> = mainRepository.getGenreMovies()
}