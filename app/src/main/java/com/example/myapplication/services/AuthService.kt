package com.example.myapplication.services


import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface AuthService {



    @POST("/partners/sandbox/v1/oauth/token")
    @Headers(
        "resourceOwnerId: l72eb1a5af8392429690292e82bda0574e",
        "requestUId: 1",
        "Content-Type: application/json")
    fun postKey(@Body body: AuthTokenBody): Call<AuthToken>


    @GET("/partners/sandbox/v1/customers/profile")
    @Headers(
        "requestUId: 1",
        "accept-language: EN")
    fun getCustomer(@Header("authorization")token: String ,
                    @Header("resourceOwnerId")resource: String): Call<CustomerProfile>


    @GET("/partners/sandbox/v2/oauth/authorize")
    @Headers(
        "accept-language: EN",
        "endState: mobile_app",
        "response-channel: mobile",
        "requestUId: 1",
        "resourceOwnerId: l72eb1a5af8392429690292e82bda0574e",
        "apisecret: d49a07dae6684b65a7d193718d5325dc\t",
        "apikey: l72eb1a5af8392429690292e82bda0574e")
    fun authorize(): Call<AuthAuthorize>



}