package com.example.moviescope.navigationBar.favList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescope.databinding.FragmentFavBinding
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieDatabase
import com.example.moviescope.screens.navigationBar.favList.FavAdapter
import com.example.moviescope.screens.navigationBar.favList.viewModel.FavFragmentViewModel
import com.example.moviescope.screens.navigationBar.favList.viewModel.FavViewModelFactory


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private lateinit var favAdapter: FavAdapter
    private lateinit var favFragmentViewModel: FavFragmentViewModel
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MovieDatabase.getDatabase(requireContext()).getMovieDao()
        movieRepository = MovieRepository(dao)
        val viewModelFactory = FavViewModelFactory(movieRepository)
        favFragmentViewModel = ViewModelProvider(this,viewModelFactory)[FavFragmentViewModel::class.java]
        favFragmentViewModel.getFavMovies.observe(viewLifecycleOwner, Observer {
            favAdapter.submitList(it)
        })
        favAdapter = FavAdapter(favFragmentViewModel)
        binding.movieFavRec.adapter = favAdapter
        binding.movieFavRec.layoutManager = LinearLayoutManager(requireContext())

    }
}






