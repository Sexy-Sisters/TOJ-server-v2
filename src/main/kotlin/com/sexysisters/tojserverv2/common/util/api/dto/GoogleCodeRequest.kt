package com.sexysisters.tojserverv2.common.util.api.dto

data class GoogleCodeRequest(
    val code: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
) {
    val grantType = "authorization_code"
}