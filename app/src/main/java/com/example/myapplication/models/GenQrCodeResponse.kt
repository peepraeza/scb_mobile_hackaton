package com.example.myapplication.models

data class GenQrCodeResponse(
    val data: Data,
    val status: Status
){
    data class Data(
        val qrImage: String,
        val qrRawData: String
    )

    data class Status(
        val code: Int,
        val description: String
    )
}

