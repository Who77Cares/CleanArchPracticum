package com.example.apitest2.network.api

import com.example.apitest2.adout.data.MovieDetailsResponse
import com.example.apitest2.movies.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Он создаёт "заготовку" запроса (объект Call<T>), но сам запрос ещё не отправляется. Это как "накопитель" с данными запроса.
interface IMDbApi {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun findMovie(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>
}