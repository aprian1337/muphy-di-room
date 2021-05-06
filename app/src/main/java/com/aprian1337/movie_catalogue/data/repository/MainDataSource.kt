package com.aprian1337.movie_catalogue.data.repository

import androidx.lifecycle.LiveData
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem

interface MainDataSource {
    fun getFeaturedMovies() : LiveData<List<MovieTv>>
    fun getNowMovies() : LiveData<List<MovieTv>>
    fun getFeaturedTvShows() : LiveData<List<MovieTv>>
    fun getOnAirTvShows() : LiveData<List<MovieTv>>
    fun getGenreMovies() : LiveData<List<GenresItem>>
    fun getGenreTvShows() : LiveData<List<GenresItem>>
    fun getDetailMovies(id : String) : LiveData<DetailMovieTv>
    fun getDetailTvShows(id : String) : LiveData<DetailMovieTv>
}