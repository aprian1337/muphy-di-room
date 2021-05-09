package com.aprian1337.movie_catalogue.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<MovieTv>>

    @Mock
    private lateinit var genreObserver: Observer<List<GenresItem>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getFeaturedMovies(){
        val movieListRepository = MutableLiveData<List<MovieTv>>()
        val moviesData = DummyData.movies
        movieListRepository.value = moviesData

        `when`(repository.getFeaturedMovies()).thenReturn(movieListRepository)
        val entity = viewModel.getFeaturedMovies().value
        verify(repository).getFeaturedMovies()
        Assert.assertNotNull(entity)

        viewModel.getFeaturedMovies().observeForever(observer)
        verify(observer).onChanged(moviesData)
    }

    @Test
    fun getListMovies(){
        val movieListRepository = MutableLiveData<List<MovieTv>>()
        val moviesData = DummyData.movies
        movieListRepository.value = moviesData

        `when`(repository.getNowMovies()).thenReturn(movieListRepository)
        val entity = viewModel.getNowMovies().value
        verify(repository).getNowMovies()
        Assert.assertNotNull(entity)

        viewModel.getNowMovies().observeForever(observer)
        verify(observer).onChanged(moviesData)
    }

    @Test
    fun getGenre(){
        val genreList = MutableLiveData<List<GenresItem>>()
        val genreData = DummyData.genres
        genreList.value = genreData

        `when`(repository.getGenreMovies()).thenReturn(genreList)
        val entity = viewModel.getGenreMovies().value
        verify(repository).getGenreMovies()
        Assert.assertNotNull(entity)

        viewModel.getGenreMovies().observeForever(genreObserver)
        verify(genreObserver).onChanged(genreData)
    }
}
