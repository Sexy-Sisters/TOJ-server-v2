package com.sexysisters.tojserverv2.infrastructure.jwt

import com.sexysisters.tojserverv2.infrastructure.jwt.exception.InvalidTokenException
import com.sexysisters.tojserverv2.infrastructure.redis.RedisRepository
import org.springframework.stereotype.Component

@Component
class JwtValidator(
    private val jwtTokenProvider: JwtTokenProvider,
    private val redisRepository: RedisRepository,
) {

    fun getEmail(token: String) = jwtTokenProvider.extractAllClaims(token)["email"].toString()

    fun validateRefreshToken(token: String) = redisRepository.getData(getEmail(token))
        ?: throw InvalidTokenException()
}