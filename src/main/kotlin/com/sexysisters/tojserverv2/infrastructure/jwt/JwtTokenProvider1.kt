package com.sexysisters.tojserverv2.infrastructure.jwt

import com.sexysisters.tojserverv2.config.properties.JwtProperties
import com.sexysisters.tojserverv2.infrastructure.jwt.exception.TokenException
import com.sexysisters.tojserverv2.infrastructure.redis.RedisRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProviderImpl(
    private val jwtProperties: JwtProperties,
    private val redisRepository: RedisRepository,
) : JwtTokenProvider {

    override fun createAccessToken(email: String) = createToken(email, jwtProperties.accessTokenValidTime)

    override fun createRefreshToken(email: String) = createToken(email, jwtProperties.refreshTokenValidTime)

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

    override fun extractAllClaims(token: String): Claims {
        if (redisRepository.hasBlackList(token)) {
            throw TokenException.AlreadyLogout()
        }
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(jwtProperties.secret))
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenException.Expired()
        } catch (e: Exception) {
            throw TokenException.Invalid()
        }
    }

    override fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader(jwtProperties.jwtHeader)
        return parseToken(bearer)
    }

    override fun parseToken(bearerToken: String?): String? {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.jwtPrefix)) {
            return bearerToken.replace(jwtProperties.jwtPrefix, "")
        }
        return null
    }

    override fun getExpiredTime(token: String): Date =
        Jwts.parserBuilder()
            .setSigningKey(getSigningKey(jwtProperties.secret))
            .build()
            .parseClaimsJws(token)
            .body
            .expiration
}