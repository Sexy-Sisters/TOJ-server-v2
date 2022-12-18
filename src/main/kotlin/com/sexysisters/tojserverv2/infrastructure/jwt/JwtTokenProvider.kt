package com.sexysisters.tojserverv2.infrastructure.jwt

import io.jsonwebtoken.Claims
import java.util.*
import javax.servlet.http.HttpServletRequest

interface JwtTokenProvider {
    fun createAccessToken(email: String): String
    fun createRefreshToken(email: String): String
    fun extractAllClaims(token: String): Claims
    fun resolveToken(request: HttpServletRequest): String?
    fun parseToken(bearerToken: String?): String?
    fun getExpiredTime(token: String): Date
}