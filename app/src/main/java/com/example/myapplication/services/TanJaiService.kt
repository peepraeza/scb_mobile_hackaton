package com.example.myapplication.services

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.*

interface TanJaiService {

    @POST("/api/user")
    fun register(@Body body: UserRegis): Call<ResponseEntity>

    @GET("/api/user/verifyUser")
    fun verifyUser(@Header ("citizenId")id: String):Call<ResponseEntity>

    @GET("/api/user/login")
    fun login(@Header ("citizenId")id: String,
              @Header ("password")password: String): Call<ResponseEntity>

    @POST("/api/pending")
    fun createPending(@Body body: PendingBody): Call<PendingRes>

    @POST("/api/pending/activeCitizenStatus")
    fun checkStatusPending(@Header ("id")pendingId: Int):Call<ResponseEntity>

    @POST("/api/pending/cancleCitizenStatus")
    fun cancelPending(@Header ("id")pendingId: Int):Call<ResponseEntity>

    @POST("/api/pending/editAtmStatus")
    fun editAtmStatus(@Header ("id")pendingId: Int,
                   @Header("status")stauts:Boolean):Call<ResponseEntity>

    @GET("/api/pending/getPendingByAtmId")
    fun getAtmPending(@Header("atmCitizenId")atmCitizenId:String): Call<PendingRes>

    @DELETE("/api/pending/deletePending")
    fun deletePending(@Header("id")pendingId: Long): Call<ResponseEntity>

    @POST("/api/user/addPoint")
    fun addPoint(@Header("citizenId")citizenId:String): Call<ResponseEntity>

//    @POST("/api/transaction")
//    fun createTransaction(@Body body: transitionModelBody): Call<TransactionRes>


    @GET("/api/reward/getReward")
    fun getReward(@Header("rewardId") rewardId: Long): Call<RewardEntity>

    @POST("/api/user/editPoint")
    fun editPoint(
        @Header("citizenId") citizenId: String,
        @Header("rewardPoint") rewardPoint: Int
    ): Call<RewardEntity>

    @GET("/api/user/getUser")
    fun getUser(@Header("citizenId") id: String): Call<ResponseEntity>

//
}


