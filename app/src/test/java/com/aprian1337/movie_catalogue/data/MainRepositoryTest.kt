package com.aprian1337.movie_catalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aprian1337.movie_catalogue.data.network.response.MoviesResponse
import com.aprian1337.movie_catalogue.data.network.response.ResponseDataMovies
import com.aprian1337.movie_catalogue.data.network.response.ResponseDataTv
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.data.repository.NetworkDataSource
import com.aprian1337.movie_catalogue.ui.main.movie.MoviesViewModel
import com.aprian1337.movie_catalogue.utils.DummyData
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {
    private lateinit var repository: MainRepository
    private val remoteApi = Mockito.mock(NetworkDataSource::class.java)
    private val fakeRepository = FakeRepository(remoteApi)
    private val call = Mockito.mock(Call::class.java)
    private val throwable = Mockito.mock(Throwable::class.java)

    private val movieResponse = Gson().fromJson(
        DummyData.movieResponse,
        ResponseDataMovies::class.java
    ) as ResponseDataMovies<MoviesResponse>
    private val tvShowResponse = Gson().fromJson(
        DummyData.tvShowsResponse,
        ResponseDataTv::class.java
    ).results

    private val movieResponse2 = DummyData.moviesResponse

    private val movies = DummyData.movies
    private val tvshows = DummyData.tvShow
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository = MainRepository(remoteApi)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getFeaturedMovies() {
        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[0] as Callback<ResponseDataMovies<MoviesResponse>>).apply {
                onResponse(call as Call<ResponseDataMovies<MoviesResponse>>, Response.success(movieResponse))
                onFailure(call, throwable)
            }
        }.`when`(remoteApi).getFeaturedMovies()
    }

}