package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo

interface AuthService {
    fun login(request: UserCommand.LoginRequest): UserInfo.Token
    fun logout(accessToken: String)
}