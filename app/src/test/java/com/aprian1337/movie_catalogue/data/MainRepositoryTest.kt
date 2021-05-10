package com.aprian1337.movie_catalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.data.repository.NetworkDataSource
import com.aprian1337.movie_catalogue.ui.main.movie.MoviesViewModel
import com.aprian1337.movie_catalogue.utils.DummyData
import com.aprian1337.movie_catalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {
    private lateinit var repository: MainRepository
    private val remoteApi = Mockito.mock(NetworkDataSource::class.java)
    private val fakeRepository = FakeRepository(remoteApi)

    private val movieResponse2 = DummyData.moviesResponse

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MainRepository(remoteApi)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getFeaturedMovies() {
        runBlocking {
            doAnswer {
                @Suppress("UNCHECKED_CAST")
                (it.arguments[0] as NetworkDataSource.loadMoviesCallback).apply {
                    onMoviesReceived(movieResponse2)
                }
            }.`when`(remoteApi).getFeaturedMovies(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getFeaturedMovies())
        runBlocking {
            verify(remoteApi).getFeaturedMovies(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.movieResponse2.size)
    }

}