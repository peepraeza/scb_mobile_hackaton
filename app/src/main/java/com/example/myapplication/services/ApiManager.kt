package com.example.myapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    val authService by lazy {
        createService<AuthService>("https://api.partners.scb/")
    }

    val tanJaiService by lazy {
        createService<TanJaiService>("http://192.168.8.104:8081/")
    }

    private inline fun <reified T> createService(baseUrl: String): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run { create(T::class.java) }
}