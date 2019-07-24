package com.example.myapplication.models

data class AuthTokenBody(
    val applicationKey: String,
    val applicationSecret: String,
    val authCode: String
)



