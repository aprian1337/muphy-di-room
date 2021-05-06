package com.aprian1337.movie_catalogue.data.network

import com.aprian1337.movie_catalogue.data.network.response.*
import com.aprian1337.movie_catalogue.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ENDPOINT_FEATURED_MOVIES)
    fun getFeaturedMovies(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("page") page : Int = 1
    ) : Call<ResponseData<MoviesResponse>>

    @GET(Constants.ENDPOINT_NOW_MOVIES)
    fun getNowMovies(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Call<ResponseData<MoviesResponse>>

    @GET(Constants.ENDPOINT_MOVIES_GENRE)
    fun getGenreMovies(
        @Query("api_key") apiKey : String = Constants.API_KEY,
    ) : Call<GenreResponse<GenresItem>>

    @GET(Constants.ENDPOINT_FEATURED_TV_SHOWS)
    fun getFeaturedTvShows(
        @Query("api_key") apiKey : String = Constants.API_KEY,
    ) : Call<ResponseData<TvShowsResponse>>

    @GET(Constants.ENDPOINT_ON_AIR_TVSHOWS)
    fun getOnAirTvShows(
        @Query("api_key") apiKey : String = Constants.API_KEY,
    ) : Call<ResponseData<TvShowsResponse>>

    @GET(Constants.ENDPOINT_TV_SHOWS_GENRE)
    fun getGenreTvShows(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Call<GenreResponse<GenresItem>>

    @GET(Constants.ENDPOINT_MOVIES_DETAIL)
    fun getMoviesDetail(
        @Path("id") id : String,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Call<DetailMoviesResponse>

    @GET(Constants.EMDPOINT_TV_SHOWS_DETAIL)
    fun getTvShowsDetail(
        @Path("id") id : String,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Call<DetailTvShowsResponse>
}