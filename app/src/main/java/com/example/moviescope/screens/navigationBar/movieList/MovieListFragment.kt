package com.example.moviescope.navigationBar.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        binding.recyclerView2.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        binding.recyclerView3.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        movieListFirstAdapter = MovieListAdapter()
        movieListSecondAdapter = MovieListAdapter()

        binding.recyclerView.adapter = movieListFirstAdapter
        binding.recyclerView2.adapter = movieListSecondAdapter

        movieListViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]

        initAdapter()
        clickBarButton()


    }

    private fun searchMovieFirst() {
        movieListViewModel.fetchMovieFirst("Spider-Man")
        movieListViewModel.moviesLiveDataFirst.observe(viewLifecycleOwner) { movies ->
            movieListFirstAdapter.movies = movies
        }
        binding.movieTextFirst.text = getString(R.string.spiderman)
        setOnClick(movieListFirstAdapter)
        binding.recyclerView.adapter = movieListFirstAdapter
    }

    private fun searchMovieSecond() {
        movieListViewModel.fetchMovieSecond("Harry Potter")
        movieListViewModel.moviesLiveDataSecond.observe(viewLifecycleOwner) { movies ->
            movieListSecondAdapter.movies = movies
        }
        binding.recyclerView2.adapter = movieListSecondAdapter
        setOnClick(movieListSecondAdapter)

    }

    private fun searchMovieThird() {
        movieListViewModel.fetchMovieThird("The Lord of the Rings")
        movieListViewModel.movieLiveDataThird.observe(viewLifecycleOwner) { movies ->
            movieListThirdAdapter.movies = movies
        }
        binding.recyclerView3.adapter = movieListThirdAdapter
        binding.movieTextThird.text = getString(R.string.the_rings)
        setOnClick(movieListThirdAdapter)

    }


    private fun setOnClick(movieList: MovieListAdapter) {
        movieList.setOnClickListiner(object : MovieListAdapter.OnClickListener {
            override fun onClick(position: Int, imdString: String) {
                val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                intent.putExtra("imd", imdString)
                startActivity(intent)
            }

        })
    }

    private fun initAdapter() {
        movieListFirstAdapter = MovieListAdapter()
        movieListSecondAdapter = MovieListAdapter()
        movieListThirdAdapter = MovieListAdapter()
        searchMovieFirst()
        searchMovieSecond()
        searchMovieThird()
    }

    private fun clickBarButton() {
        binding.searchBar.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }
}
