package com.sexysisters.tojserverv2.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@ConstructorBinding
@Validated
@ConfigurationProperties(prefix = "spring.jwt")
data class JwtProperties(
    @NotEmpty
    val accessTokenValidTime: Long,
    @NotEmpty
    val refreshTokenValidTime: Long,
    @NotEmpty
    val secret: String,
) {
    val jwtPrefix = "Bearer"
    val jwtHeader = "Authorization"
}