package com.aprian1337.movie_catalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.*
import com.aprian1337.movie_catalogue.data.repository.MainDataSource
import com.aprian1337.movie_catalogue.data.repository.NetworkDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FakeRepository (private val networkDataSource: NetworkDataSource) :
    MainDataSource {

    val featuredMoviesList = MutableLiveData<List<MovieTv>>()
    val nowMoviesList = MutableLiveData<List<MovieTv>>()
    val featuredTvShowsList = MutableLiveData<List<MovieTv>>()
    val onAirTvShowsList = MutableLiveData<List<MovieTv>>()
    val genreMovieList = MutableLiveData<List<GenresItem>>()
    val genresTvShowsList = MutableLiveData<List<GenresItem>>()
    val detailMovies = MutableLiveData<DetailMovieTv>()
    val detailTvShow = MutableLiveData<DetailMovieTv>()

    override fun getGenreTvShows(): LiveData<List<GenresItem>> {
        GlobalScope.launch {
            networkDataSource.getGenreTvShows(object : NetworkDataSource.LoadGenreCallback {
                override fun onGenreReceived(genreResponse: List<GenresItem>) {
                    genresTvShowsList.postValue(genreResponse)
                }
            })
        }
        return genresTvShowsList
    }

    override fun getGenreMovies(): LiveData<List<GenresItem>> {
        GlobalScope.launch {
            networkDataSource.getGenreMovies(object : NetworkDataSource.LoadGenreCallback {
                override fun onGenreReceived(genreResponse: List<GenresItem>) {
                    genreMovieList.postValue(genreResponse)
                }
            })
        }
        return genreMovieList
    }

    override fun getFeaturedMovies(): LiveData<List<MovieTv>> {
        GlobalScope.launch {
            networkDataSource.getFeaturedMovies(object : NetworkDataSource.LoadMoviesCallback {
                override fun onMoviesReceived(movieResponse: List<MoviesResponse>) {
                    val list = mutableListOf<MovieTv>()
                    for (data in movieResponse) {
                        list.add(
                            MovieTv(
                                data.id,
                                data.title,
                                data.genreIds,
                                data.releaseDate,
                                data.overview,
                                data.posterPath,
                                data.voteAverage.toString(),
                            )
                        )
                    }
                    featuredMoviesList.postValue(list)
                }
            })
        }
        return featuredMoviesList
    }

    override fun getNowMovies(): LiveData<List<MovieTv>> {
        GlobalScope.launch {
            networkDataSource.getNowMovies(object : NetworkDataSource.LoadMoviesCallback {
                override fun onMoviesReceived(movieResponse: List<MoviesResponse>) {
                    val list = mutableListOf<MovieTv>()
                    for (data in movieResponse) {
                        list.add(
                            MovieTv(
                                data.id,
                                data.title,
                                data.genreIds,
                                data.releaseDate,
                                data.overview,
                                data.posterPath,
                                data.voteAverage.toString(),
                            )
                        )
                    }
                    nowMoviesList.postValue(list)
                }
            })
        }
        return nowMoviesList
    }

    override fun getFeaturedTvShows(): LiveData<List<MovieTv>> {
        GlobalScope.launch {
            networkDataSource.getFeaturedTvShows(object : NetworkDataSource.LoadTvShowsCallback {
                override fun onTvShowsReceived(tvShowsResponse: List<TvShowsResponse>) {
                    val list = mutableListOf<MovieTv>()
                    for (data in tvShowsResponse) {
                        list.add(
                            MovieTv(
                                data.id,
                                data.name,
                                data.genreIds,
                                data.firstAirDate,
                                data.overview,
                                data.posterPath,
                                data.voteAverage.toString(),
                            )
                        )
                    }
                    featuredTvShowsList.postValue(list)
                }
            })
        }
        return featuredTvShowsList
    }

    override fun getOnAirTvShows(): LiveData<List<MovieTv>> {
        GlobalScope.launch {
            networkDataSource.getOnAirTvShows(object : NetworkDataSource.LoadTvShowsCallback {
                override fun onTvShowsReceived(tvShowsResponse: List<TvShowsResponse>) {
                    val list = mutableListOf<MovieTv>()
                    for (data in tvShowsResponse) {
                        list.add(
                            MovieTv(
                                data.id,
                                data.name,
                                data.genreIds,
                                data.firstAirDate,
                                data.overview,
                                data.posterPath,
                                data.voteAverage.toString(),
                            )
                        )
                    }
                    onAirTvShowsList.postValue(list)
                }
            })
        }
        return onAirTvShowsList
    }

    override fun getDetailMovies(id: String): LiveData<DetailMovieTv> {
        GlobalScope.launch {
            networkDataSource.getMoviesDetail(id, object :
                NetworkDataSource.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(detailResponse: DetailMoviesResponse) {
                    detailResponse.apply {
                        var stringGenre = ""
                        for (genre in this.genres) {
                            stringGenre += if (genre.id != this.genres[this.genres.size-1].id) {
                                genre.name + ", "
                            } else {
                                genre.name
                            }
                        }
                        val data = DetailMovieTv(
                            this.originalTitle,
                            stringGenre,
                            this.releaseDate,
                            this.overview,
                            this.posterPath,
                            this.voteAverage.toString()
                        )
                        detailMovies.postValue(data)
                    }
                }
            })
        }
        return detailMovies
    }

    override fun getDetailTvShows(id: String): LiveData<DetailMovieTv> {
        GlobalScope.launch {
            networkDataSource.getTvShowsDetail(id, object :
                NetworkDataSource.LoadDetailTvShowCallback {
                override fun onDetailTvShowReceived(detailResponse: DetailTvShowsResponse) {
                    detailResponse.apply {
                        var stringGenre = ""
                        for (genre in this.genres) {
                            stringGenre += if (genre.id != this.genres[this.genres.size-1].id) {
                                genre.name + ", "
                            } else {
                                genre.name
                            }
                        }
                        val data = DetailMovieTv(
                            this.originalName,
                            stringGenre,
                            this.firstAirDate,
                            this.overview,
                            this.posterPath,
                            this.voteAverage.toString()
                        )
                        detailTvShow.postValue(data)
                    }
                }
            })
        }
        return detailTvShow
    }
}