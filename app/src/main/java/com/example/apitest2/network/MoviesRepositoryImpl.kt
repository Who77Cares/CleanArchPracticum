package com.example.apitest2.network

import android.util.Log
import com.example.apitest2.adout.data.MovieDetailsRequest
import com.example.apitest2.adout.data.MovieDetailsResponse
import com.example.apitest2.adout.domain.MovieDetails
import com.example.apitest2.network.api.NetworkClient
import com.example.apitest2.movies.data.dto.MovieSearchRequest
import com.example.apitest2.movies.data.dto.MoviesSearchResponse
import com.example.apitest2.network.api.MoviesRepository
import com.example.apitest2.movies.domain.models.Movie
import com.example.apitest2.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMoviesRep(expression: String): Resource<List<Movie>> {

        val response = networkClient.doRequest(MovieSearchRequest(expression))
        Log.d("REPO", "resultCode=${response.resultCode}")

        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                val movieResponse = response as MoviesSearchResponse
                val result = movieResponse.results

                if (result.isEmpty()) {
                    Resource.Error("ничего не найдено")
                } else {
                    Resource.Success(result.map {
                        Movie(it.id, it.resultType, it.image, it.title, it.description)
                    })
                }

            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id = id,
                            title = title,
                            imDbRating = imDbRating,
                            year = year,
                            countries = countries,
                            genres = genres,
                            directors = directors,
                            writers = writers,
                            stars = stars,
                            plot = plot,
                        )
                    )
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}