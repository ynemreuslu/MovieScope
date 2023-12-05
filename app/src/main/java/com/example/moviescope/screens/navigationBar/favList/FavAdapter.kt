package com.example.moviescope.screens.navigationBar.favList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviescope.R
import com.example.moviescope.databinding.FavItemBinding
import com.example.moviescope.diff.DiffCallBack
import com.example.moviescope.navigationBar.favList.FavFragment
import com.example.moviescope.room.MovieModel
import com.example.moviescope.screens.navigationBar.favList.viewModel.FavFragmentViewModel

class FavAdapter(private val favFragmentViewModel: FavFragmentViewModel) :
    ListAdapter<MovieModel, FavAdapter.FavViewHolder>(
        DiffCallBack<MovieModel>(
            itemTheSame = { newItem, oldItem -> newItem == oldItem },
            contentsTheSame = { newItem, oldItem -> newItem == oldItem })
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavViewHolder(
        FavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class FavViewHolder(val binding: FavItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieModel: MovieModel) {
            binding.favMovieTitle.text = movieModel.movieTitle
            binding.favMovieYear.text = movieModel.movieYear
            Glide.with(binding.root).load(movieModel.moviePoster).into(binding.favPoster)

            binding.favImageButton.setOnClickListener {
                favFragmentViewModel.movieDelete(movieModel.id)
                binding.favImageButton.setImageResource(R.drawable.ic_favorite_border)
            }

        }
    }


}