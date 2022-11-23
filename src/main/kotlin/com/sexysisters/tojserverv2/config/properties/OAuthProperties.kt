package com.sexysisters.tojserverv2.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "oauth")
data class OAuthProperties(
    val google: OAuth,
) {

    data class OAuth(
        val baseUrl: String,
        val clientId: String,
        val clientSecret: String,
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