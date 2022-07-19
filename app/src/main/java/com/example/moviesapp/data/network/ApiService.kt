package com.example.moviesapp.data.network

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviesResult
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/top_rated?api_key=0c922e3f7a1d769ece700b8b8f11ffe5&language=en-US&page=1")
    fun fetchAllMovies(): Call<MoviesResult>
}