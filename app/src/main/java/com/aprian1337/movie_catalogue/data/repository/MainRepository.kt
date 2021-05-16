package com.aprian1337.movie_catalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aprian1337.movie_catalogue.data.LocalDataSource
import com.aprian1337.movie_catalogue.data.NetworkDataSource
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
    ) :
    MainDataSource {

    val featuredMoviesList = MutableLiveData<List<MovieTv>>()
    val nowMoviesList = MutableLiveData<List<MovieTv>>()
    val featuredTvShowsList = MutableLiveData<List<MovieTv>>()
    val onAirTvShowsList = MutableLiveData<List<MovieTv>>()
    val genreMovieList = MutableLiveData<List<GenresItem>>()
    val genresTvShowsList = MutableLiveData<List<GenresItem>>()
    val detailMovies = MutableLiveData<DetailMovieTv>()
    val detailTvShow = MutableLiveData<DetailMovieTv>()

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(networkData: NetworkDataSource, localData :LocalDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(networkData, localData).apply {
                    instance = this
                }
            }
    }

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
            networkDataSource.getMoviesDetail(id, object : NetworkDataSource.LoadDetailMovieCallback {
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
                            this.id,
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
            networkDataSource.getTvShowsDetail(id, object : NetworkDataSource.LoadDetailTvShowCallback {
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
                            this.id,
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

    override suspend fun addFav(favoriteEntity: FavoriteEntity) = localDataSource.addFav(favoriteEntity)
    override fun getAllFav(type: String): LiveData<PagedList<FavoriteEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFav(type), config).build()
    }

    override fun checkFav(type: String, id: Int): LiveData<Int> {
        val data = MutableLiveData<Int>()
        GlobalScope.launch {
            data.postValue(localDataSource.checkFav(type, id))
        }
        return data
    }

    override suspend fun deleteFav(favoriteEntity: FavoriteEntity) = localDataSource.deleteFav(favoriteEntity)

}