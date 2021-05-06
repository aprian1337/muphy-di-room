package com.aprian1337.movie_catalogue.data.repository

import com.aprian1337.movie_catalogue.data.network.ApiClient

class NetworkDataSource constructor(private val apiClient : ApiClient){
    companion object{
        @Volatile
        private var instance: NetworkDataSource? = null
        fun getInstance(apiClient: ApiClient) : NetworkDataSource =
            instance ?: synchronized (this) {
                instance ?: NetworkDataSource(apiClient).apply {
                    instance = this
                }
            }
    }
    fun getFeaturedMovies() = apiClient.api.getFeaturedMovies()
    fun getNowMovies() = apiClient.api.getNowMovies()
    fun getGenreMovies() = apiClient.api.getGenreMovies()
    fun getGenreTvShows() = apiClient.api.getGenreTvShows()
    fun getFeaturedTvShows() = apiClient.api.getFeaturedTvShows()
    fun getOnAirTvShows() = apiClient.api.getOnAirTvShows()
    fun getMoviesDetail(id : String) = apiClient.api.getMoviesDetail(id = id)
    fun getTvShowsDetail(id : String) = apiClient.api.getTvShowsDetail(id = id)
}