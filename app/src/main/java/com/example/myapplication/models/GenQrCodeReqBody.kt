package com.example.myapplication.models

data class GenQrCodeReqBody(
    val amount: String,
    val ppId: String,
    val ppType: String,
    val qrType: String,
    val ref1: String,
    val ref2: String,
    val ref3: String
)