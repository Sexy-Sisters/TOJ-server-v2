package com.sexysisters.tojserverv2.infrastructure.oauth

import com.sexysisters.tojserverv2.common.util.api.oauth.dto.OAuthInfoResponse

interface OAuthExecutor {
    fun support(oAUthMethod: OAuthMethod) = oAUthMethod == OAuthMethod.GOOGLE
    fun getLink(): String
    fun execute(code: String): OAuthInfoResponse
}
