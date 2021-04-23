package com.aprian1337.movie_catalogue.ui.main.tvshow

import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.models.MovieTv

class TvShowViewModel : ViewModel() {
    private var tvShow = emptyList<MovieTv>()
    private var tvShowFeatured = emptyList<MovieTv>()

    fun setTvShow(data : ArrayList<MovieTv>){
       tvShow = data.toMutableList()
    }

    fun setTvShowFeatured(data : ArrayList<MovieTv>){
        tvShowFeatured = data.toMutableList()
    }

    fun getTvShow() : List<MovieTv> = tvShow

    fun getTvShowFeatured() : List<MovieTv> = tvShowFeatured
}