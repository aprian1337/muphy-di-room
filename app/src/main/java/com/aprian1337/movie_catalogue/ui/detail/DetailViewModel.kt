package com.aprian1337.movie_catalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.repository.MainRepository

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getDetailMovies(id : Int) : LiveData<DetailMovieTv> = mainRepository.getDetailMovies(id.toString())
    fun getDetailTvShows(id : Int) : LiveData<DetailMovieTv> = mainRepository.getDetailTvShows(id.toString())
}