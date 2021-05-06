package com.aprian1337.movie_catalogue.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponseData<T>(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<T>,

	@field:SerializedName("total_results")
	val totalResults: Int
)