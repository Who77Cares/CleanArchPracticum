package com.example.apitest2.adout.ui

import com.example.apitest2.adout.domain.MovieDetails

sealed interface AboutState {
    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState
}