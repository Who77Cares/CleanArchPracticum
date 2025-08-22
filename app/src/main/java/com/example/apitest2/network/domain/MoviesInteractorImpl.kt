package com.example.apitest2.network.domain

import com.example.apitest2.network.api.MoviesInteractor
import com.example.apitest2.network.api.MoviesRepository
import com.example.apitest2.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMoviesInt(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            val resource = repository.searchMoviesRep(expression)

            when(resource) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }

    override fun getMoviesDetails(
        movieId: String,
        consumer: MoviesInteractor.MovieDetailsConsumer
    ) {
        executor.execute {
            when(val resource = repository.getMovieDetails(movieId)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }


}