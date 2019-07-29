package com.example.myapplication.services

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.*

interface UserArea {

    @POST("/api/location/nActive")
    fun getAllNearUser(@Body body: ListUserAreaBody): Call<ListUserAreaReq>

    @POST("/api/location")
    fun createLatLong(@Body body: LocationModelBody): Call<ResponseEntity>

    @GET("/api/location")
    fun getLatLong(@Header("citizenId")citizenId: String): Call<LocationModelRes>

    @DELETE("/api/location/deleteLocation")
    fun deleteLocation(@Header ("activeStatus")activeStatus:Boolean,
                       @Header("citizenId")citizenId:String): Call<ResponseEntity>
}

