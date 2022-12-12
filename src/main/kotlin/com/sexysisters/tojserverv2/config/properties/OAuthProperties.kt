package com.sexysisters.tojserverv2.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@ConstructorBinding
@Validated
@ConfigurationProperties(prefix = "oauth")
data class OAuthProperties(
    val google: OAuth,
) {

    data class OAuth(
        @NotEmpty
        val baseUrl: String,
        @NotEmpty
        val clientId: String,
        @NotEmpty
        val clientSecret: String,
        @NotEmpty
        val redirectUrl: String,
    )
}

fun OAuthProperties.googleBaseUrl() = google.baseUrl

fun OAuthProperties.googleClientId() = google.clientId

fun OAuthProperties.googleClientSecret() = google.clientSecret

fun OAuthProperties.googleRedirectUrl() = google.redirectUrl