package com.example.apitest2.cast.ui

import com.example.apitest2.cast.domain.MovieCastPerson

interface MoviesCastRVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem


}