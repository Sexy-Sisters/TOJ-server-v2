package com.sexysisters.tojserverv2.domain.auth

class AuthInfo {

    class Token(
        val accessToken: String,
        val refreshToken: String? = null,
    )
}