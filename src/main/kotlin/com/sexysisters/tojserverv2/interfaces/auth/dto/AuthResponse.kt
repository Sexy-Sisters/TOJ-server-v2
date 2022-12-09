package com.sexysisters.tojserverv2.interfaces.auth.dto

class AuthResponse {

    class Token(
        val accessToken: String,
        val refreshToken: String? = null,
    )
}