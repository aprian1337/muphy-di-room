package com.aprian1337.movie_catalogue.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponseDataMovies<T>(
	@field:SerializedName("results")
	val results: List<MoviesResponse>,
)