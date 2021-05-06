package com.aprian1337.movie_catalogue.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.movie_catalogue.data.di.Injection
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.ui.detail.DetailViewModel
import com.aprian1337.movie_catalogue.ui.main.movie.MoviesViewModel
import com.aprian1337.movie_catalogue.ui.main.tvshow.TvShowViewModel

class AppViewModelFactory(private val repository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: AppViewModelFactory? = null

        fun getInstance(): AppViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: AppViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else-> {
                throw Throwable("Unidentified view model "+modelClass.name)
            }
        }
    }
}