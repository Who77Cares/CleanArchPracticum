package com.example.apitest2.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.apitest2.network.api.NetworkClient
import com.example.apitest2.adout.data.MovieDetailsRequest
import com.example.apitest2.movies.data.dto.MovieSearchRequest
import com.example.apitest2.movies.data.dto.Response
import com.example.apitest2.network.api.IMDbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitNetworkClient(private val context: Context) : NetworkClient {

    private val imdbBaseUrl = "https://tv-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(imdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val imdbService = retrofit.create(IMDbApi::class.java)

    override fun doRequest(dto: Any): Response {

        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        return when(dto) {
            is MovieSearchRequest -> {
                val resp = imdbService.findMovie(dto.expression).execute()
                val body = resp.body() ?: Response()
                body.apply {
                    resultCode = resp.code()

                }
            }

            is MovieDetailsRequest -> {
                val resp = imdbService.getMovieDetails(dto.movieId).execute()
                Log.d("NET", "details code=${resp.code()} msg=${resp.message()}")
                val body = resp.body() ?: Response()
                body.apply { resultCode = resp.code() }
            }

            else -> {
                Response().apply { resultCode = 400 }
            }

        }


    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

}