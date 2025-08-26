package com.example.apitest2.cast.ui

import com.example.apitest2.cast.domain.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<MoviesCastRVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}