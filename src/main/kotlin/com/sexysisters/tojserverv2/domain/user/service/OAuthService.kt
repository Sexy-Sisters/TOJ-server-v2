package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserInfo

interface OAuthService {
    fun getGoogleLink(): String
    fun googleLogin(code: String): UserInfo.Token
}