package com.example.myapplication.models
import com.google.gson.annotations.SerializedName


data class LocationModelRes(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Status
){
    data class Status(
        @SerializedName("code")
        val code: Int,
        @SerializedName("message")
        val message: String
    )

    data class Data(
        @SerializedName("activeStatus")
        val activeStatus: Boolean,
        @SerializedName("citizenId")
        val citizenId: String,
        @SerializedName("latitude")
        val latitude: Double,
        @SerializedName("longitude")
        val longitude: Double,
        @SerializedName("moneyLimit")
        val moneyLimit: Int
    )
}

