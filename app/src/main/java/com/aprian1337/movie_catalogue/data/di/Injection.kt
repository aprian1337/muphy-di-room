package com.aprian1337.movie_catalogue.data.di

import com.aprian1337.movie_catalogue.data.network.ApiClient
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.data.repository.NetworkDataSource

object Injection {
    fun provideRepository() : MainRepository{
        val networkDataSource = NetworkDataSource.getInstance(ApiClient)
        return MainRepository.getInstance(networkDataSource)
    }
}