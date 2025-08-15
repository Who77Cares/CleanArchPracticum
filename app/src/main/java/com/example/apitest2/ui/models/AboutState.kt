package com.example.apitest2.ui.models

import com.example.apitest2.domain.models.MovieDetails

sealed interface AboutState {
    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState
}