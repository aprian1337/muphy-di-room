package com.aprian1337.movie_catalogue.ui.main.tvshow

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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

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
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getFeaturedTvShows(){
        val tvShow = MutableLiveData<List<MovieTv>>()
        val tvShowData = DummyData.tvShow
        tvShow.value = tvShowData

        Mockito.`when`(repository.getFeaturedTvShows()).thenReturn(tvShow)
        val entity = viewModel.getFeaturedTvShows().value
        verify(repository).getFeaturedTvShows()
        Assert.assertNotNull(entity)

        viewModel.getFeaturedTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowData)
    }

    @Test
    fun getListMovies(){
        val tvShow = MutableLiveData<List<MovieTv>>()
        val tvShowData = DummyData.tvShow
        tvShow.value = tvShowData

        Mockito.`when`(repository.getOnAirTvShows()).thenReturn(tvShow)
        val entity = viewModel.getOnAirTvShows().value
        verify(repository).getOnAirTvShows()
        Assert.assertNotNull(entity)

        viewModel.getOnAirTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowData)
    }

    @Test
    fun getGenre(){
        val genreList = MutableLiveData<List<GenresItem>>()
        val genreData = DummyData.genres
        genreList.value = genreData

        Mockito.`when`(repository.getGenreTvShows()).thenReturn(genreList)
        val entity = viewModel.getGenreTvShows().value
        verify(repository).getGenreTvShows()
        Assert.assertNotNull(entity)

        viewModel.getGenreTvShows().observeForever(genreObserver)
        verify(genreObserver).onChanged(genreData)
    }

}