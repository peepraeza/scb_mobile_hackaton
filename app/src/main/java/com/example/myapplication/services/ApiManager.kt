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


    val getUser by lazy {
        createService<GetUser>("https://reqres.in/")
    }

    private inline fun <reified T> createService(baseUrl: String): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(T::class.java) }
}