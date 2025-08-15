package com.example.apitest2.domain.api

import com.example.apitest2.domain.models.Movie
import com.example.apitest2.domain.models.MovieDetails

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

