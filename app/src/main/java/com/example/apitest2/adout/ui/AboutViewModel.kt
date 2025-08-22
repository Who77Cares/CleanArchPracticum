package com.example.apitest2.adout.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest2.adout.domain.MovieDetails
import com.example.apitest2.network.api.MoviesInteractor

class AboutViewModel(private val movieId: String,
                     private val moviesInteractor: MoviesInteractor
): ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {


        moviesInteractor.getMoviesDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {
            override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
                Log.d("ABOUT_VM", "consume: hasMovie=${movieDetails != null} err=$errorMessage")
                if (movieDetails != null) {
                    stateLiveData.postValue(AboutState.Content(movieDetails))
                } else {
                    stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
                }
            }
        })
    }

}