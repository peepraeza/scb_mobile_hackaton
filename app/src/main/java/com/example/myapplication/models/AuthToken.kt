package com.example.myapplication.models

data class AuthToken(
    val status: Status,
    val data: Data
)

data class Status(
    val code: Int,
    val description: String
)

data class Data(
    val accessToken: String,
    val refreshToken: String
)