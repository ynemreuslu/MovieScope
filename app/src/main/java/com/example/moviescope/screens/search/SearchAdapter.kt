package com.example.moviescope.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviescope.databinding.SearchItemBinding
import com.example.moviescope.diff.DiffCallBack
import com.example.moviescope.models.Movie

class SearchAdapter(private val onSearchClick: (Movie) -> Unit) :
    ListAdapter<Movie, SearchAdapter.SearchViewHolder>(
        DiffCallBack<Movie>(
            itemTheSame = { newItem, oldItem -> newItem == oldItem },
            contentsTheSame = { newItem, oldItem -> newItem == oldItem })
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class SearchViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.searchMovieTitle.text = movie.Title
            binding.searchMovieYear.text = movie.Year
            binding.root.setOnClickListener {
                onSearchClick(movie)

            }
        }
    }
}