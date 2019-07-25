package com.example.myapplication.services

import com.example.myapplication.models.ResponseEntity
import com.example.myapplication.models.UserRegis
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TanJaiService {

    @POST("/api/user")
    fun register(@Body body: UserRegis): Call<ResponseEntity>

    @GET("/api/user/verifyUser")
    fun verifyUser(@Header ("citizenId")id: String):Call<ResponseEntity>

    @GET("/api/user/login")
    fun login(@Header ("citizenId")id: String,
              @Header ("password")password: String): Call<ResponseEntity>
}

