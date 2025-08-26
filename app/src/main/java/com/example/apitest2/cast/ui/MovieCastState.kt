package com.example.apitest2.cast.ui

import com.example.apitest2.RVItem
import com.example.apitest2.cast.domain.MovieCast

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}