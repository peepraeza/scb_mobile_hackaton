package com.example.myapplication.services

import com.example.myapplication.models.ListUserAreaBody
import com.example.myapplication.models.ListUserAreaReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserArea {

    @POST("/api/location/nActive")
    fun getAllNearUser(@Body body: ListUserAreaBody): Call<ListUserAreaReq>
}
