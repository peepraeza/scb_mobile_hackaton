package com.example.myapplication.models
import com.google.gson.annotations.SerializedName


data class PendingRes(
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
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("atmCitizenId")
        val atmCitizenId: String,
        @SerializedName("atmStatus")
        val atmStatus: Boolean,
        @SerializedName("citizenId")
        val citizenId: String,
        @SerializedName("citizenStatus")
        val citizenStatus: Boolean,
        @SerializedName("pendingId")
        val pendingId: Int
    )
}

