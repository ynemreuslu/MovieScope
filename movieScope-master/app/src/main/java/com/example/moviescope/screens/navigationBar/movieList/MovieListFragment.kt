package com.example.moviescope.navigationBar.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviescope.databinding.FragmentMovieBinding
import com.example.moviescope.screens.movieDetail.MovieDetailsActivity
import com.example.moviescope.screens.navigationBar.movieList.MovieListAdapter
import com.example.moviescope.screens.navigationBar.movieList.MovieListViewModel
import com.example.moviescope.screens.search.SearchActivity



class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieListAdapter: MovieListAdapter
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
        movieListAdapter = MovieListAdapter()
        binding.recyclerView.adapter = movieListAdapter
        binding.recyclerView2.adapter = movieListAdapter
        binding.recyclerView3.adapter = movieListAdapter

        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieListViewModel.getAllMovies("Harry Potter")


        movieListViewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
            movieListAdapter.movies = movies
        }



        movieListAdapter.setOnClickListiner(object : MovieListAdapter.OnClickListener {
            override fun onClick(position: Int, imdString: String) {
                val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                intent.putExtra("imd",imdString)
                startActivity(intent)

            }

        })



        binding.searchBar.setOnClickListener {
            val intent = Intent(requireContext(),SearchActivity::class.java)
            startActivity(intent)
        }
    }

}









