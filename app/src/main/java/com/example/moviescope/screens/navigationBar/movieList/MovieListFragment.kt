package com.example.moviescope.navigationBar.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviescope.R
import com.example.moviescope.databinding.FragmentMovieBinding
import com.example.moviescope.screens.movieDetail.MovieDetailsActivity
import com.example.moviescope.screens.navigationBar.movieList.adapter.MovieListAdapter
import com.example.moviescope.screens.navigationBar.movieList.MovieListViewModel
import com.example.moviescope.screens.search.SearchActivity


class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieListFirstAdapter: MovieListAdapter
    private lateinit var movieListSecondAdapter: MovieListAdapter
    private lateinit var movieListThirdAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        binding.viewModel = movieListViewModel
        movieListViewModel.setFragment(this)
        binding.lifecycleOwner = viewLifecycleOwner

        movieListViewModel.fetchMovieFirst("Spider-Man")
        movieListViewModel.fetchMovieSecond("Harry Potter")
        movieListViewModel.fetchMovieThird("The Lord of the Rings")

        movieListFirstAdapter = MovieListAdapter { imdbId ->
            handleMovieItemClick(imdbId)
        }

        movieListSecondAdapter = MovieListAdapter { imdbId ->
            handleMovieItemClick(imdbId)
        }

        movieListThirdAdapter = MovieListAdapter { imdbId ->
            handleMovieItemClick(imdbId)
        }

        setupRecyclerView()
        observeMovieLists()

    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = movieListFirstAdapter
        }
        binding.recyclerView2.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = movieListSecondAdapter
        }

        binding.recyclerView3.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = movieListThirdAdapter
        }
    }

    private fun observeMovieLists() {
        movieListViewModel.moviesLiveDataFirst.observe(viewLifecycleOwner) { movies ->
            movieListFirstAdapter.movies = movies
        }
        movieListViewModel.moviesLiveDataSecond.observe(viewLifecycleOwner) { movies ->
            movieListSecondAdapter.movies = movies
        }
        movieListViewModel.movieLiveDataThird.observe(viewLifecycleOwner) { movies ->
            movieListThirdAdapter.movies = movies
        }
    }

    private fun observeSelectedMovie() {
        movieListViewModel.selectedMovieId.observe(viewLifecycleOwner) { selectedMovieId ->
            if (selectedMovieId != null) {
                navigateToMovieDetails(selectedMovieId)
                movieListViewModel.onMovieItemClick(selectedMovieId)
            }
        }
    }

    private fun handleMovieItemClick(imdbId: String) {
        navigateToMovieDetails(imdbId)
        movieListViewModel.onMovieItemClick(imdbId)
    }

    private fun navigateToMovieDetails(imdbId: String) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("imd", imdbId)
        startActivity(intent)
    }

    fun clickBarButton() {
        val intent = Intent(requireContext(), SearchActivity::class.java)
        startActivity(intent)
    }
}
