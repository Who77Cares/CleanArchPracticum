package com.example.apitest2.adout.data

import com.example.apitest2.movies.data.dto.Response

class MovieDetailsResponse(val id: String,
                           val title: String,
                           val imDbRating: String,
                           val year: String,
                           val countries: String,
                           val genres: String,
                           val directors: String,
                           val writers: String,
                           val stars: String,
                           val plot: String) : Response()