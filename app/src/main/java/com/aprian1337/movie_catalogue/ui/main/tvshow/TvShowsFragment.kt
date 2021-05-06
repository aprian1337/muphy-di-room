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
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.ui.AppViewModelFactory
import com.aprian1337.movie_catalogue.ui.main.MovieTvAdapter
import com.aprian1337.movie_catalogue.ui.main.MovieTvFeaturedAdapter
import com.aprian1337.movie_catalogue.ui.detail.DetailActivity

class TvShowsFragment : Fragment() {

    private val movieTvAdapter by lazy { MovieTvAdapter() }
    private val movieTvFeaturedAdapter by lazy { MovieTvFeaturedAdapter() }
    private lateinit var viewModel: TvShowViewModel
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object{
        private const val TAG = "TVSHOWS"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setFeaturedLoading(true)
        setListLoading(true)
        viewModel = ViewModelProvider(
            this,
            AppViewModelFactory.getInstance()
        ).get(TvShowViewModel::class.java)
        viewModel.apply {
            getFeaturedTvShows().observe(viewLifecycleOwner, {
                movieTvFeaturedAdapter.setFeaturedMovieTv(it)
                setFeaturedLoading(false)
            })

            getOnAirTvShows().observe(viewLifecycleOwner, {
                movieTvAdapter.setMovieTv(it)
                setListLoading(false)
            })

            getGenreTvShows().observe(viewLifecycleOwner, {
                movieTvAdapter.setGenreMovieTv(it)
                movieTvFeaturedAdapter.setGenreMovieTv(it)
            })
        }
        

        movieTvAdapter.setOnItemClickCallback(object : MovieTvAdapter.OnItemClickCallback {
            override fun onItemClicked(data : MovieTv) {
                selectedUser(data)
            }
        })
        movieTvFeaturedAdapter.setOnItemClickCallback(object :
            MovieTvFeaturedAdapter.OnItemClickCallback {
            override fun onItemClicked(data : MovieTv) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(data : MovieTv) {
        Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TYPE_TAG, TAG)
            putExtra(DetailActivity.EXTRA_ID_MOVIETV, data.id)
            startActivity(this)
        }
    }

    private fun setupRv() {
        with(binding) {
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

    private fun setFeaturedLoading(state : Boolean){
        if(state){
            binding.pbFeatured.visibility = View.VISIBLE
        }else{
            binding.pbFeatured.visibility = View.GONE
        }
    }

    private fun setListLoading(state: Boolean){
        if(state){
            binding.pbList.visibility = View.VISIBLE
        }else{
            binding.pbList.visibility = View.GONE
        }
    }
}