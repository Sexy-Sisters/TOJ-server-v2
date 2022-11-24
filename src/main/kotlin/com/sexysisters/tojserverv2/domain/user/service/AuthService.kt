package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo

interface AuthService {
    fun getGoogleLink(): String
    fun googleLogin(code: UserCommand.GoogleLoginRequest): UserInfo.Token
}