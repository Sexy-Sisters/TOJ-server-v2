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

fun OAuthProperties.googleBaseUrl(): String {
    return google.baseUrl
}

fun OAuthProperties.googleClientId(): String {
    return google.clientId
}

fun OAuthProperties.googleClientSecret(): String {
    return google.clientSecret
}

fun OAuthProperties.googleRedirectUrl(): String {
    return google.redirectUrl
}