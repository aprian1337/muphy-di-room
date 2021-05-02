package com.aprian1337.movie_catalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.models.MovieTv

class DetailViewModel : ViewModel() {

    private var movieTv = MovieTv()

    fun setMovieTv(list : MovieTv){
        movieTv = list
    }

    fun getMovieTv() : MovieTv =
}