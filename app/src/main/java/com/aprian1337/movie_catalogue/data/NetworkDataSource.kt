package com.aprian1337.movie_catalogue.data

import com.aprian1337.movie_catalogue.data.network.ApiClient
import com.aprian1337.movie_catalogue.data.network.response.*
import com.aprian1337.movie_catalogue.utils.EspressoIdlingResource
import retrofit2.await

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
    suspend fun getFeaturedMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getFeaturedMovies()
        response.await().results.apply {
            callback.onMoviesReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getNowMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getNowMovies()
        response.await().results.apply {
            callback.onMoviesReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getFeaturedTvShows(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getFeaturedTvShows()
        response.await().results.apply {
            callback.onTvShowsReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getOnAirTvShows(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getOnAirTvShows()
        response.await().results.apply {
            callback.onTvShowsReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getGenreMovies(callback: LoadGenreCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getGenreMovies()
        response.await().genres.apply {
            callback.onGenreReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getGenreTvShows(callback: LoadGenreCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getGenreTvShows()
        response.await().genres.apply {
            callback.onGenreReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMoviesDetail(id : String, callback: LoadDetailMovieCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getMoviesDetail(id)
        response.await().apply {
            callback.onDetailMovieReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowsDetail(id : String, callback: LoadDetailTvShowCallback){
        EspressoIdlingResource.increment()
        val response = apiClient.api.getTvShowsDetail(id)
        response.await().apply {
            callback.onDetailTvShowReceived(
                this
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesCallback{
        fun onMoviesReceived(movieResponse: List<MoviesResponse>)
    }

    interface LoadTvShowsCallback{
        fun onTvShowsReceived(tvShowsResponse: List<TvShowsResponse>)
    }

    interface LoadDetailMovieCallback{
        fun onDetailMovieReceived(detailResponse: DetailMoviesResponse)
    }

    interface LoadDetailTvShowCallback{
        fun onDetailTvShowReceived(detailResponse: DetailTvShowsResponse)
    }

    interface LoadGenreCallback{
        fun onGenreReceived(genreResponse: List<GenresItem>)
    }
}