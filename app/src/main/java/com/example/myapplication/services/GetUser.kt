package com.example.myapplication.services

import com.example.myapplication.models.ListUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface GetUser {

    @GET("/api/users?page=2")
    fun getAllUser(): Call<ListUser>
}
