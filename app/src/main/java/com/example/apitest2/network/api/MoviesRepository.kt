package com.example.apitest2.network.api

import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.adout.domain.MovieDetails
import com.example.apitest2.cast.domain.MovieCast
import com.example.apitest2.util.Resource

interface MoviesRepository {
    fun searchMoviesRep(expression: String) : Resource<List<Movie>>

    fun getMovieDetails(movieId: String): Resource<MovieDetails>

    fun getMovieCast(movieId: String): Resource<MovieCast>
}