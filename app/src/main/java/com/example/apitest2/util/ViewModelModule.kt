package com.example.apitest2.util

import com.example.apitest2.presentation.about.AboutViewModel
import com.example.apitest2.presentation.movies.MoviesViewModel
import com.example.apitest2.presentation.poster.PosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {


    viewModel {
        MoviesViewModel(androidContext(), get(), get())
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

}