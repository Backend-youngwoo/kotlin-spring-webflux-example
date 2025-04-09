package com.example.market.common.auth

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)