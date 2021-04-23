package com.aprian1337.movie_catalogue.ui.main.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.movie_catalogue.databinding.FragmentTvShowsBinding
import com.aprian1337.movie_catalogue.models.MovieTv
import com.aprian1337.movie_catalogue.ui.main.MovieTvAdapter
import com.aprian1337.movie_catalogue.ui.main.MovieTvFeaturedAdapter
import com.aprian1337.movie_catalogue.ui.detail.DetailActivity
import com.aprian1337.movie_catalogue.utils.DummyData

class TvShowsFragment : Fragment() {

    private val movieTvAdapter by lazy { MovieTvAdapter() }
    private val movieTvFeaturedAdapter by lazy { MovieTvFeaturedAdapter() }
    private lateinit var viewModel : TvShowViewModel
    private var _binding : FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel::class.java)
        viewModel.setTvShow(DummyData.getTvShows())
        viewModel.setTvShowFeatured(DummyData.getFeaturedTvShows())
        movieTvAdapter.setMovieTv(viewModel.getTvShow())
        movieTvFeaturedAdapter.setFeaturedMovieTv(viewModel.getTvShowFeatured())
        movieTvAdapter.setOnItemClickCallback(object : MovieTvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTv) {
                selectedUser(data)
            }
        })
        movieTvFeaturedAdapter.setOnItemClickCallback(object : MovieTvFeaturedAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTv) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(data: MovieTv) {
        Intent(activity, DetailActivity::class.java).apply {
            val dataParcelable = MovieTv(
                data.id,
                data.title,
                data.genre,
                data.length,
                data.overview,
                data.image,
                data.parental
            )
            putExtra(DetailActivity.EXTRA_MOVIE_TV, dataParcelable)
            startActivity(this)
        }
    }

    private fun setupRv(){
        with(binding){
            rvListTvShows.apply {
                adapter = movieTvAdapter
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
            }
            rvFeaturedTvShow.apply {
                adapter = movieTvFeaturedAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }
}