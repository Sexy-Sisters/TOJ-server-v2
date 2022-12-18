package com.sexysisters.tojserverv2.infrastructure.jwt

import com.sexysisters.tojserverv2.infrastructure.jwt.exception.TokenException
import com.sexysisters.tojserverv2.infrastructure.redis.RedisRepository
import org.springframework.stereotype.Component

@Component
class JwtValidatorImpl(
    private val jwtTokenProvider: JwtTokenProvider,
    private val redisRepository: RedisRepository,
) : JwtValidator {

    override fun getEmail(token: String) = jwtTokenProvider.extractAllClaims(token)["email"].toString()

    override fun validateRefreshToken(token: String) =
        redisRepository.getData(getEmail(token))
            ?: throw TokenException.Invalid()
}