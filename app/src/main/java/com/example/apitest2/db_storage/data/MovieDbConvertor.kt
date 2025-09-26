package com.example.apitest2.db_storage.data

import com.example.apitest2.movies.domain.models.Movie

class MovieDbConvertor {

    fun map(movie: Movie): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }



}