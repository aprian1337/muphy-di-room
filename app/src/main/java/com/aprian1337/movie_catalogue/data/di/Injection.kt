package com.aprian1337.movie_catalogue.data.di

import android.content.Context
import com.aprian1337.movie_catalogue.data.LocalDataSource
import com.aprian1337.movie_catalogue.data.NetworkDataSource
import com.aprian1337.movie_catalogue.data.local.AppDatabase
import com.aprian1337.movie_catalogue.data.network.ApiClient
import com.aprian1337.movie_catalogue.data.repository.MainRepository

object Injection {
    fun provideRepository(context: Context) : MainRepository{
        val networkDataSource = NetworkDataSource.getInstance(ApiClient)
        val favDao = AppDatabase.getDatabase(context)?.favoriteUserDAO()
        val localDataSource = LocalDataSource.getInstance(favDao!!)
        return MainRepository.getInstance(networkDataSource, localDataSource)
    }
}