package com.aprian1337.movie_catalogue.ui.main.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.databinding.FragmentFavoriteListBinding
import com.aprian1337.movie_catalogue.ui.AppViewModelFactory
import com.aprian1337.movie_catalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class FavoriteListFragment : Fragment() {

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!
    private val favoriteMovieAdapter by lazy { FavoriteMovieAdapter() }
    private val favoriteTvAdapter by lazy { FavoriteTvShowAdapter() }
    private lateinit var viewModel : FavoriteViewModel

    companion object{
        private const val INDEX_TABS = "index_tabs"

        fun newInstance(idx: Int) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putInt(INDEX_TABS, idx)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        val idxTabs = arguments?.getInt(INDEX_TABS)
        if (idxTabs != null) {
            getSwipe(idxTabs).attachToRecyclerView(binding.rvFavorite)
        }
        _binding = FragmentFavoriteListBinding.bind(view)
        viewModel = ViewModelProvider(this, AppViewModelFactory.getInstance(activity?.applicationContext!!)).get(
            FavoriteViewModel::class.java)
        favoriteTvAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: FavoriteEntity) {
                selectedUser(data)
            }
        })
        favoriteMovieAdapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: FavoriteEntity) {
                selectedUser(data)
            }
        })
        when(idxTabs) {
            0-> {
                setupRv(favoriteMovieAdapter)
                viewModel.getAllFavMovie().observe(viewLifecycleOwner, {
                    favoriteMovieAdapter.submitList(it)
                    showLoading(false)
                })
            }
            1-> {
                setupRv(favoriteTvAdapter)
                viewModel.getAllFavTvShows().observe(viewLifecycleOwner, {
                    favoriteTvAdapter.submitList(it)
                    showLoading(false)
                })
            }
        }
    }

    private fun selectedUser(data: FavoriteEntity) {
        val id = data.id
        val TAG = data.type
        Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TYPE_TAG, TAG)
            putExtra(DetailActivity.EXTRA_ID_MOVIETV, id)
            startActivity(this)
        }
    }

    private fun <T : Any> setupRv(rvAdapter : PagedListAdapter<T, *>){
        with(binding){
            rvFavorite.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun getSwipe(idxTabs: Int): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (view != null) {
                    val swipedPosition = viewHolder.adapterPosition
                    when(idxTabs){
                        0->{
                            val favoriteEntity = favoriteMovieAdapter.getSwipeData(swipedPosition)
                            favoriteEntity?.let { viewModel.setFav(it) }

                            val snackbar = Snackbar.make(view as View, "Undo removed from favorite movies", Snackbar.LENGTH_LONG)
                            snackbar.setAction("Undo") {
                                favoriteEntity?.let { viewModel.addFav(it) }
                            }
                            snackbar.show()
                        }
                        1->{
                            val favoriteEntity = favoriteTvAdapter.getSwipeData(swipedPosition)
                            favoriteEntity?.let { viewModel.setFav(it) }

                            val snackbar = Snackbar.make(view as View, "Undo removed from favorite tv shows", Snackbar.LENGTH_LONG)
                            snackbar.setAction("Undo") {
                                favoriteEntity?.let { viewModel.addFav(it) }
                            }
                            snackbar.show()
                        }
                    }

                }
            }
        })
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.pbFavorite.visibility = View.VISIBLE
        }else{
            binding.pbFavorite.visibility = View.GONE
        }
    }
}