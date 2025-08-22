package com.example.apitest2.network.api

import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.adout.domain.MovieDetails

interface MoviesInteractor {

    fun searchMoviesInt(expression: String, consumer: MoviesConsumer)
    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }
}

