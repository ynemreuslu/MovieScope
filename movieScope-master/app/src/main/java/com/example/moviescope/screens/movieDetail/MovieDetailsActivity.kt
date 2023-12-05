package com.example.moviescope.screens.movieDetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviescope.databinding.ActivityMovieDetailsBinding
import com.example.moviescope.movieDetail.MovieDetailViewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPres()
        movieDetailViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        val imdId = intent.getStringExtra("imd")

        imdId?.let { movieDetailViewModel.getDetailsMovies(it) }

        observeViewModel()

    }


    private fun onBackPres() {
        binding.topAppBar.setNavigationOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun observeViewModel() {

        movieDetailViewModel.movieDetails.observe(this) { movieDetails ->
            if (movieDetails != null) {
                binding.movieDetailsPicture.let { imageView ->
                    Glide.with(this).load(movieDetails.Poster).into(imageView)
                }

                binding.movieDetailsActor.text = "Actor : ${movieDetails.Actors}"
                binding.movieDetailsTitle.text = "Title : ${movieDetails.Title}"
                binding.movieDetailsDirector.text = "Director : ${movieDetails.Director}"
                binding.movieDetailsWriter.text = "Writer : ${movieDetails.Writer}"
                binding.movieDetailsIMD.text = "IMD : ${movieDetails.imdbRating}"

            } else Log.e("Movie Details Activity", "Movie not found")

        }

    }
}




