package com.example.apitest2.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.apitest2.network.api.NetworkClient
import com.example.apitest2.adout.data.MovieDetailsRequest
import com.example.apitest2.cast.data.CastRequest
import com.example.apitest2.network.models.movie.MovieSearchRequest
import com.example.apitest2.network.models.Response
import com.example.apitest2.network.api.IMDbApi
import com.example.apitest2.network.models.person.NamesSearchRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

        return when (dto) {
            is MovieSearchRequest -> {
                val resp = imdbService.findMovie(dto.expression).execute()
                val body = resp.body() ?: Response()
                body.apply {
                    resultCode = resp.code()

                }
            }

            is MovieDetailsRequest -> {
                val resp = imdbService.getMovieDetails(dto.movieId).execute()

                val body = resp.body() ?: Response()
                body.apply { resultCode = resp.code() }
            }

            // получаем каст
            is CastRequest -> {
                val resp = imdbService.getFullCast(dto.movieId).execute()

                val body = resp.body() ?: Response()
                body.apply { resultCode = resp.code() }
            }

//            is NamesSearchRequest -> {
//                val resp = imdbService.searchNames(dto.expression).execute()
//
//                val body = resp.body() ?: Response()
//                body.apply { resultCode = resp.code() }
//            }

            else -> {
                Response().apply { resultCode = 400 }
            }
        }
    }

    override suspend fun doRequestSuspend(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }

        if (dto !is NamesSearchRequest) {
            return Response().apply { resultCode = 400 }
        }


        /*
        withContext(Dispatchers.IO) переключает выполнение текущей корутины на специальный поток, предназначенный
        для операций ввода-вывода (например, сетевых запросов, работы с файлами, базами данных), чтобы не блокировать главный поток (UI).

        Это помогает:
        1. Выполнять долгие операции асинхронно, не замедляя работу интерфейса.
        2. Использовать оптимизированный пул потоков для I/O задач, чтобы избежать блокировки потока, в котором выполняется код.
         */

        val result = withContext(Dispatchers.IO) {
            try {
                val response = imdbService.searchNames(dto.expression)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
        return result
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