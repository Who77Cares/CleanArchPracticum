package com.example.apitest2.network.models.movie

import com.example.apitest2.network.models.Response

class MoviesSearchResponse(val searchType: String,
                           val expression: String,
                           val results: List<MovieDto>) : Response()  {
}