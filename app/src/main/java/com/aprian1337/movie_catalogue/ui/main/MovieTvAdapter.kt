package com.aprian1337.movie_catalogue.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.databinding.ListMovieTvBinding
import com.aprian1337.movie_catalogue.models.MovieTv
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieTvAdapter : RecyclerView.Adapter<MovieTvAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: ListMovieTvBinding) : RecyclerView.ViewHolder(binding.root)

    val movieTv = mutableListOf<MovieTv>()

    private var setOnItemClickCallback : OnItemClickCallback? = null
    
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.setOnItemClickCallback = onItemClickCallback
    }
    
    fun setMovieTv(list : List<MovieTv>){
        movieTv.addAll(list.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movieTv = movieTv[position]
        holder.binding.apply {
            Glide.with(root.context)
                .load(movieTv.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            tvTitle.text = movieTv.title
            tvGenre.text = movieTv.genre
            tvLength.text = movieTv.length
            root.setOnClickListener{
                setOnItemClickCallback?.onItemClicked(movieTv)
            }
        }
    }

    override fun getItemCount(): Int = movieTv.size
    
    interface OnItemClickCallback{
        fun onItemClicked(data: MovieTv)
    }
}