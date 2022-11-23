package com.sexysisters.tojserverv2.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.jwt")
data class JwtProperties(
    val accessTokenValidTime: Long,
    val refreshTokenValidTime: Long,
    val secret: String,
) {
    val jwtPrefix = "Bearer"
    val jwtHeader = "Authorization"
}