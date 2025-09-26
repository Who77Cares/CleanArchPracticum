package com.example.apitest2.db_storage.data

import androidx.room.Database

@Database(version = 1, entities = [MovieEntity::class])
abstract class AppDatabase {

    abstract fun movieDao(): MovieDao

}