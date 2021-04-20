package com.aprian1337.movie_catalogue.ui.main.moviesfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.movie_catalogue.models.MovieTv
import com.aprian1337.movie_catalogue.utils.DummyData

class MoviesViewModel : ViewModel() {
    private val movies = MutableLiveData<ArrayList<MovieTv>>()
    private val featuredMovies = MutableLiveData<ArrayList<MovieTv>>()
    fun getMovies() : LiveData<ArrayList<MovieTv>> {
        movies.postValue(DummyData.getMovies())
        return movies
    }

    fun getFeaturedMovies() : LiveData<ArrayList<MovieTv>> {
        featuredMovies.postValue(DummyData.getFeaturedMovies())
        return featuredMovies
    }
}