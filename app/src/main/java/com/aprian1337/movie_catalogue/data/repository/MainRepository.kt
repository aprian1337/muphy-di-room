package com.aprian1337.movie_catalogue.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository private constructor(private val networkDataSource: NetworkDataSource) :
    MainDataSource {

    val featuredMoviesList = MutableLiveData<List<MovieTv>>()
    val nowMoviesList = MutableLiveData<List<MovieTv>>()
    val featuredTvShowsList = MutableLiveData<List<MovieTv>>()
    val genreMovieList = MutableLiveData<List<GenresItem>>()
    val genresTvShowsList = MutableLiveData<List<GenresItem>>()

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(networkData: NetworkDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(networkData).apply {
                    instance = this
                }
            }
    }

    override fun getGenreTvShows(): LiveData<List<GenresItem>> {
        val genres = networkDataSource.getGenreTvShows()
        GlobalScope.launch {
            genres.enqueue(object : Callback<GenreResponse<GenresItem>> {
                override fun onResponse(
                    call: Call<GenreResponse<GenresItem>>,
                    response: Response<GenreResponse<GenresItem>>
                ) {
                    genresTvShowsList.postValue(response.body()?.genres!!)
                }

                override fun onFailure(
                    call: Call<GenreResponse<GenresItem>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }

            })
        }
        return genresTvShowsList
    }

    override fun getGenreMovies(): LiveData<List<GenresItem>> {
        val genres = networkDataSource.getGenreMovies()
        GlobalScope.launch {
            genres.enqueue(object : Callback<GenreResponse<GenresItem>> {
                override fun onResponse(
                    call: Call<GenreResponse<GenresItem>>,
                    response: Response<GenreResponse<GenresItem>>
                ) {
                    if (response.isSuccessful) {
                        genreMovieList.postValue(response.body()?.genres!!)
                    }
                }

                override fun onFailure(
                    call: Call<GenreResponse<GenresItem>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
        return genreMovieList
    }

    override fun getFeaturedMovies(): LiveData<List<MovieTv>> {
        val featuredMoviesResponse = networkDataSource.getFeaturedMovies()
        GlobalScope.launch {
            featuredMoviesResponse.enqueue(object : Callback<ResponseData<MoviesResponse>> {
                override fun onResponse(
                    call: Call<ResponseData<MoviesResponse>>,
                    response: Response<ResponseData<MoviesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movieList = mutableListOf<MovieTv>()
                        for (datas in response.body()?.results!!) {

                            movieList.add(
                                MovieTv(
                                    datas.id,
                                    datas.title,
                                    datas.genreIds,
                                    datas.releaseDate,
                                    datas.overview,
                                    datas.posterPath,
                                    datas.voteAverage.toString(),
                                )
                            )
                        }
                        featuredMoviesList.postValue(movieList)
                    }
                }

                override fun onFailure(
                    call: Call<ResponseData<MoviesResponse>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
        return featuredMoviesList
    }

    override fun getNowMovies(): LiveData<List<MovieTv>> {
        val nowMoviesResponse = networkDataSource.getNowMovies()
        runBlocking {
            nowMoviesResponse.enqueue(object : Callback<ResponseData<MoviesResponse>> {
                override fun onResponse(
                    call: Call<ResponseData<MoviesResponse>>,
                    response: Response<ResponseData<MoviesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movieList = mutableListOf<MovieTv>()
                        for (datas in response.body()?.results!!) {
                            movieList.add(
                                MovieTv(
                                    datas.id,
                                    datas.title,
                                    datas.genreIds,
                                    datas.releaseDate,
                                    datas.overview,
                                    datas.posterPath,
                                    datas.voteAverage.toString(),
                                )
                            )
                        }
                        nowMoviesList.postValue(movieList)
                    }
                }

                override fun onFailure(
                    call: Call<ResponseData<MoviesResponse>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
        return nowMoviesList
    }

    override fun getFeaturedTvShows(): LiveData<List<MovieTv>> {
        val featuredTvShows = networkDataSource.getFeaturedTvShows()
        GlobalScope.launch {
            featuredTvShows.enqueue(object : Callback<ResponseData<TvShowsResponse>> {
                override fun onResponse(
                    call: Call<ResponseData<TvShowsResponse>>,
                    response: Response<ResponseData<TvShowsResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movieList = mutableListOf<MovieTv>()
                        for (datas in response.body()?.results!!) {
                            movieList.add(
                                MovieTv(
                                    datas.id,
                                    datas.name,
                                    datas.genreIds,
                                    datas.firstAirDate,
                                    datas.overview,
                                    datas.posterPath,
                                    datas.voteAverage.toString(),
                                )
                            )
                        }
                        featuredTvShowsList.postValue(movieList)
                    }
                }

                override fun onFailure(
                    call: Call<ResponseData<TvShowsResponse>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
        return featuredTvShowsList
    }

    override fun getOnAirTvShows(): LiveData<List<MovieTv>> {
        val onAirTvShows = networkDataSource.getOnAirTvShows()
        GlobalScope.launch {
            onAirTvShows.enqueue(object : Callback<ResponseData<TvShowsResponse>> {
                override fun onResponse(
                    call: Call<ResponseData<TvShowsResponse>>,
                    response: Response<ResponseData<TvShowsResponse>>
                ) {
                    if (response.isSuccessful) {
                        val movieList = mutableListOf<MovieTv>()
                        for (datas in response.body()?.results!!) {

                            movieList.add(
                                MovieTv(
                                    datas.id,
                                    datas.name,
                                    datas.genreIds,
                                    datas.firstAirDate,
                                    datas.overview,
                                    datas.posterPath,
                                    datas.voteAverage.toString(),
                                )
                            )
                        }
                        nowMoviesList.postValue(movieList)
                    }
                }

                override fun onFailure(
                    call: Call<ResponseData<TvShowsResponse>>,
                    t: Throwable
                ) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
        return nowMoviesList
    }

    override fun getDetailMovies(id: String): LiveData<DetailMovieTv> {
        val detailMoviesResponse = networkDataSource.getMoviesDetail(id)
        val detailMovies = MutableLiveData<DetailMovieTv>()
        detailMoviesResponse.enqueue(object : Callback<DetailMoviesResponse> {
            override fun onResponse(
                call: Call<DetailMoviesResponse>,
                response: Response<DetailMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    with(response.body()) {
                        var stringGenre = ""
                        for (genre in this?.genres!!) {
                            if (genre.id != this.genres[genres.size - 1].id) {
                                stringGenre += genre.name + ", "
                            } else {
                                stringGenre += genre.name
                            }
                        }
                        detailMovies.postValue(
                            DetailMovieTv(
                                this.title,
                                stringGenre,
                                this.releaseDate,
                                this.overview,
                                this.posterPath,
                                this.voteAverage.toString()
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<DetailMoviesResponse>, t: Throwable) {
                Log.d("fail", t.message.toString())
            }
        })
        return detailMovies
    }

    override fun getDetailTvShows(id: String): LiveData<DetailMovieTv> {
        val detailTvShowsResponse = networkDataSource.getTvShowsDetail(id)
        val detailTvShows = MutableLiveData<DetailMovieTv>()
        detailTvShowsResponse.enqueue(object : Callback<DetailTvShowsResponse> {
            override fun onResponse(
                call: Call<DetailTvShowsResponse>,
                response: Response<DetailTvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    with(response.body()) {
                        var stringGenre = ""
                        for (genre in this?.genres!!) {
                            if (genre.id != this.genres[genres.size - 1].id) {
                                stringGenre += genre.name + ", "
                            } else {
                                stringGenre += genre.name
                            }
                        }
                        detailTvShows.postValue(
                            DetailMovieTv(
                                this.originalName,
                                stringGenre,
                                this.firstAirDate,
                                this.overview,
                                this.posterPath,
                                this.voteAverage.toString()
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<DetailTvShowsResponse>, t: Throwable) {
                Log.d("fail", t.message.toString())
            }
        })
        return detailTvShows
    }
}