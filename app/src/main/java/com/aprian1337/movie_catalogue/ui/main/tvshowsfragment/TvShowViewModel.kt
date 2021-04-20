package com.aprian1337.movie_catalogue.ui.main.tvshowsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.models.MovieTv
import com.aprian1337.movie_catalogue.utils.DummyData

class TvShowViewModel : ViewModel() {

    private val tvShow = MutableLiveData<ArrayList<MovieTv>>()
    private val tvShowFeatured = MutableLiveData<ArrayList<MovieTv>>()

    fun getTvShow() : LiveData<ArrayList<MovieTv>>{
        tvShow.postValue(DummyData.getTvShows())
        return tvShow
    }

    fun getTvShowFeatured() : LiveData<ArrayList<MovieTv>>{
        tvShowFeatured.postValue(DummyData.getFeaturedTvShows())
        return tvShowFeatured
    }
}