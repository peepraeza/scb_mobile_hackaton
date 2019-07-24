package com.example.myapplication.models


data class AuthAuthorize(
    val data: Data,
    val status: Status
) {
    data class Data(
        val callbackUrl: String
    )

    data class Status(
        val code: Int,
        val description: String
    )
}

