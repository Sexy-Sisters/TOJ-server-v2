package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.auth.AuthInfo

interface OAuthService {
    fun getGoogleLink(): String
    fun googleLogin(code: String): AuthInfo.Token
}