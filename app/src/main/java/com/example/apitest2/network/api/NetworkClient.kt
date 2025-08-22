package com.example.apitest2.network.api

import com.example.apitest2.movies.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response // лучше dto тут переименовать в request
}