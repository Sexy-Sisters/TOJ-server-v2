package com.sexysisters.tojserverv2.infrastructure.jwt

interface JwtValidator {
    fun getEmail(token: String): String
    fun validateRefreshToken(token: String): Any
}