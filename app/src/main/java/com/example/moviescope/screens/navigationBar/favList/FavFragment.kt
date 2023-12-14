package com.example.moviescope.screens.navigationBar.favList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescope.databinding.FragmentFavBinding
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieDatabase



class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private lateinit var favAdapter: FavAdapter
    private lateinit var favFragmentViewModel: FavFragmentViewModel
    private lateinit var movieRepository: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = MovieDatabase.getInstance(requireContext()).movieDao()
        movieRepository = MovieRepository(dao)
        val viewModelFactory = FavFragmentViewModelFactory(movieRepository)
        favFragmentViewModel =
            ViewModelProvider(this, viewModelFactory)[FavFragmentViewModel::class.java]

        favAdapter = FavAdapter(favFragmentViewModel)

        binding.movieFavRec.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favAdapter
        }

        favFragmentViewModel.getFavMovies.observe(viewLifecycleOwner) {
            favAdapter.submitList(it)
        }
    }
}
