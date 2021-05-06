package com.aprian1337.movie_catalogue.utils

import com.aprian1337.movie_catalogue.BuildConfig

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val ENDPOINT_FEATURED_MOVIES = "movie/popular"
    const val ENDPOINT_NOW_MOVIES = "movie/popular"
    const val ENDPOINT_MOVIES_GENRE = "genre/movie/list"
    const val ENDPOINT_FEATURED_TV_SHOWS = "tv/popular"
    const val ENDPOINT_ON_AIR_TVSHOWS = "tv/on_the_air"
    const val ENDPOINT_TV_SHOWS_GENRE = "genre/tv/list"
    const val EMDPOINT_TV_SHOWS_DETAIL = "tv/{id}"
    const val ENDPOINT_MOVIES_DETAIL = "movie/{id}"
    const val API_KEY = BuildConfig.AUTH_TOKEN
}