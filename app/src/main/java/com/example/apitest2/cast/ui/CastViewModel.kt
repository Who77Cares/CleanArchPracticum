package com.example.apitest2.cast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest2.cast.domain.MovieCast
import com.example.apitest2.network.api.MoviesInteractor

class CastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
): ViewModel() {

    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {
        stateLiveData.postValue(MoviesCastState.Loading)

        moviesInteractor.getCast(movieId, object: MoviesInteractor.MovieCastConsumer {
            override fun consume(
                movieCast: MovieCast?,
                errorMessage: String?
            ) {
                if (movieCast != null) {
                    stateLiveData.postValue(MoviesCastState.Content(movieCast))
                } else {
                    stateLiveData.postValue(MoviesCastState.Error(errorMessage ?: "Unknown error"))
                }
            }

        })
    }
}