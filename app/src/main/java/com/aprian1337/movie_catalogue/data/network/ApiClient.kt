package com.aprian1337.movie_catalogue.data.network

import com.aprian1337.movie_catalogue.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}