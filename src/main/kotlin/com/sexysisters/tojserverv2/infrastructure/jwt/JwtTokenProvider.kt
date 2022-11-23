package com.sexysisters.tojserverv2.infrastructure.jwt

import com.sexysisters.tojserverv2.config.properties.JwtProperties
import com.sexysisters.tojserverv2.infrastructure.jwt.exception.ExpiredTokenException
import com.sexysisters.tojserverv2.infrastructure.jwt.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
) {

    fun createAccessToken(email: String) = createToken(email, jwtProperties.accessTokenValidTime)

    fun createRefreshToken(email: String) = createToken(email, jwtProperties.refreshTokenValidTime)

    private fun getSigningKey(secretKey: String): Key {
        val keyBytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun createToken(email: String, time: Long): String {
        val claims = Jwts.claims()
        claims["email"] = email
        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + time))
            .signWith(getSigningKey(jwtProperties.secret), SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractAllClaims(token: String): Claims {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(jwtProperties.secret))
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader(jwtProperties.jwtHeader)
        return parseToken(bearer)
    }

    fun parseToken(bearerToken: String?): String? {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.jwtPrefix)) {
            return bearerToken.replace(jwtProperties.jwtPrefix, "")
        }
        return null
    }

    fun getExpiredTime(token: String): Date = Jwts.parserBuilder()
        .setSigningKey(getSigningKey(jwtProperties.secret))
        .build()
        .parseClaimsJws(token)
        .body
        .expiration
}