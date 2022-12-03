package com.sexysisters.tojserverv2.infrastructure.oauth

import com.sexysisters.tojserverv2.common.util.api.oauth.client.GoogleAuthClient
import com.sexysisters.tojserverv2.common.util.api.oauth.client.GoogleInfoClient
import com.sexysisters.tojserverv2.common.util.api.oauth.dto.GoogleCodeRequest
import com.sexysisters.tojserverv2.common.util.api.oauth.dto.GoogleInfoResponse
import com.sexysisters.tojserverv2.config.properties.OAuthProperties
import com.sexysisters.tojserverv2.config.properties.googleBaseUrl
import com.sexysisters.tojserverv2.config.properties.googleClientId
import com.sexysisters.tojserverv2.config.properties.googleClientSecret
import com.sexysisters.tojserverv2.config.properties.googleRedirectUrl
import org.springframework.stereotype.Service
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Service
class GoogleAuthExecutor(
    private val oAuthProperties: OAuthProperties,
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient,
) {

    private val QUERY_STRING =
        "?client_id=%s" +
            "&redirect_uri=%s" +
            "&response_type=code" +
            "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"

    fun getLink(): String {
        return oAuthProperties.googleBaseUrl() +
            String.format(
                QUERY_STRING,
                oAuthProperties.googleClientId(),
                oAuthProperties.googleRedirectUrl()
            )
    }

    fun execute(code: String): GoogleInfoResponse {

        val codeRequest = GoogleCodeRequest(
            code = URLDecoder.decode(code, StandardCharsets.UTF_8),
            clientId = oAuthProperties.googleClientId(),
            clientSecret = oAuthProperties.googleClientSecret(),
            redirectUri = oAuthProperties.googleRedirectUrl(),
        )

        val accessToken = googleAuthClient.googleAuth(codeRequest).accessToken

        val response = googleInfoClient.googleInfo(accessToken)

        return response
    }
}