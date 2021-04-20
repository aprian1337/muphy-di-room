package com.aprian1337.movie_catalogue.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.databinding.ListFeaturedBinding
import com.aprian1337.movie_catalogue.models.MovieTv
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieTvFeaturedAdapter : RecyclerView.Adapter<MovieTvFeaturedAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: ListFeaturedBinding) : RecyclerView.ViewHolder(binding.root)

    val featuredMovieTv = mutableListOf<MovieTv>()

    fun setFeaturedMovieTv(list : ArrayList<MovieTv>){
        featuredMovieTv.addAll(list.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = featuredMovieTv[position]
        with(holder.binding){
            Glide.with(root.context)
                .load(data.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFeatured)
            tvFeaturedTitle.text = data.title
        }
    }

    override fun getItemCount(): Int = featuredMovieTv.size
}