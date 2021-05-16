package com.aprian1337.movie_catalogue.ui.main.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.databinding.ListMovieTvBinding
import com.aprian1337.movie_catalogue.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FavoriteTvShowAdapter : PagedListAdapter<FavoriteEntity, FavoriteTvShowAdapter.FavoriteViewHolder>(
    DIFF_CALLBACK) {

    private var setOnItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.setOnItemClickCallback = onItemClickCallback
    }

    inner class FavoriteViewHolder(private val binding: ListMovieTvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(fav: FavoriteEntity) {
            with(binding) {
                tvTitle.text = fav.title
                tvGenre.text = fav.genres
                tvPopularity.text = fav.vote
                Glide.with(root.context)
                    .load(Constants.BASE_IMAGE_URL + fav.img)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
                root.setOnClickListener{
                    setOnItemClickCallback?.onItemClicked(fav)
                }
            }
        }
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        if(favorite!=null){
            holder.bind(favorite)
        }
    }

    fun getSwipeData(swipedPosition: Int) : FavoriteEntity?= getItem(swipedPosition)

    interface OnItemClickCallback{
        fun onItemClicked(data: FavoriteEntity)
    }
}