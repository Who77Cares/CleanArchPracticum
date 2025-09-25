package com.example.apitest2.network.api

import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.adout.domain.MovieDetails
import com.example.apitest2.cast.domain.MovieCast

interface MoviesInteractor {

    fun searchMoviesInt(expression: String, consumer: MoviesConsumer)
    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)

    fun getCast(movieId: String, consumer: MovieCastConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }

    interface MovieCastConsumer {
        fun consume(movieCast: MovieCast?, errorMessage: String?)
    }
}


