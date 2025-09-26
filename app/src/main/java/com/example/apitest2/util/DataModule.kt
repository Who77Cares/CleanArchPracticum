package com.example.apitest2.util

import androidx.room.Room
import com.example.apitest2.db_storage.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    // ...

   single {
       Room.databaseBuilder(androidContext(), AppDatabase::class.java, "db")
           .build()
   }


}