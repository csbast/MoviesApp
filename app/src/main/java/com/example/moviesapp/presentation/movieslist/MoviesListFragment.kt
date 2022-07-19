package com.example.moviesapp.presentation.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MoviesListFragmentBinding
import com.example.moviesapp.presentation.adapter.MoviesAdapter

class MoviesListFragment : Fragment() {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var binding: MoviesListFragmentBinding
    private lateinit var mAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movies_list_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setupNetwork()
        setupObservers()
        setupUi()
    }

    private fun setupUi() {
        mAdapter = MoviesAdapter(mutableListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun setupObservers() {
        setupListObserver()
    }

    private fun setupListObserver() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) { movies ->
            mAdapter.updateMovies(movies)
        }
    }
}