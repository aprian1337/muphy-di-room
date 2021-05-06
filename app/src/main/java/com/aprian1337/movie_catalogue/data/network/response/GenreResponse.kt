package com.aprian1337.movie_catalogue.data.network.response

import com.google.gson.annotations.SerializedName

data class GenreResponse<T>(

	@field:SerializedName("genres")
	val genres: List<GenresItem>
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
