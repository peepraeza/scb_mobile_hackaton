package com.example.myapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    val scbService by lazy {
        createService<ScbService>("https://api.partners.scb/")
    }

    val tanJaiService by lazy {
        createService<TanJaiService>("http://192.168.101.57:8081/")
    }


    val userArea by lazy {
        createService<UserArea>("http://192.168.101.38:8082/")
    }

    private inline fun <reified T> createService(baseUrl: String): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(T::class.java) }
}