package com.example.apitest2.network.api

import com.example.apitest2.network.models.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response // лучше dto тут переименовать в request

    suspend fun doRequestSuspend(dto: Any): Response

}