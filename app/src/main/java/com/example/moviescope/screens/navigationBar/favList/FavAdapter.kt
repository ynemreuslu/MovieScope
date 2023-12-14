package com.example.moviescope.screens.navigationBar.favList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviescope.databinding.FavItemBinding
import com.example.moviescope.diff.DiffCallBack
import com.example.moviescope.room.MovieModel

class FavAdapter(private val favFragmentViewModel: FavFragmentViewModel) :
    ListAdapter<MovieModel, FavAdapter.FavViewHolder>(
        DiffCallBack<MovieModel>(
            itemTheSame = { newItem, oldItem -> newItem.id == oldItem.id },
            contentsTheSame = { newItem, oldItem -> newItem == oldItem })
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(
            FavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class FavViewHolder(val binding: FavItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.movieModel = movie
            Glide.with(binding.root).load(movie.poster).into(binding.favPoster)

            binding.favImageButton.setOnClickListener {
                favFragmentViewModel.movieDelete(movie)
            }
        }
    }
}
