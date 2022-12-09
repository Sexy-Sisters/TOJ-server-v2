package com.sexysisters.tojserverv2.domain.auth.service

import com.sexysisters.tojserverv2.domain.auth.AuthCommand
import com.sexysisters.tojserverv2.domain.auth.AuthInfo

interface AuthService {
    fun login(command: AuthCommand.LoginRequest): AuthInfo.Token
    fun logout(accessToken: String)
    fun getNewAccessToken(refreshToken: String): String
    fun sendCode(command: AuthCommand.SendCodeRequest)
    fun authenticateCode(command: AuthCommand.AuthenticateCode): Boolean
}