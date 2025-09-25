package com.example.apitest2.movies.data.storage

interface StorageClient<T> {

    fun storageData(data: T)
    fun getData(): T?
}