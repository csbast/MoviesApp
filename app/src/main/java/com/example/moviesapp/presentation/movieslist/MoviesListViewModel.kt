package com.example.moviesapp.presentation.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.network.RetrofitInstance
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListViewModel : ViewModel() {

    private val _movieListLiveData = MutableLiveData<List<Movie>>()
    val movieListLiveData: LiveData<List<Movie>> get() = _movieListLiveData

    fun setupNetwork() {

        RetrofitInstance.api.fetchAllMovies().enqueue(object : Callback<MoviesResult> {
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                val movies = response.body()!!
                val moviesList = movies.results

                _movieListLiveData.value = moviesList
            }
            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                Log.d("Teste", "Api Request Failure")
            }
        })
    }

}