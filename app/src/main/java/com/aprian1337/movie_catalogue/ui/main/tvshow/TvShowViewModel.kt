package com.aprian1337.movie_catalogue.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.data.repository.MainRepository

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getFeaturedTvShows() : LiveData<List<MovieTv>> = mainRepository.getFeaturedTvShows()
    fun getOnAirTvShows() : LiveData<List<MovieTv>> = mainRepository.getOnAirTvShows()
    fun getGenreTvShows() : LiveData<List<GenresItem>> = mainRepository.getGenreTvShows()
}