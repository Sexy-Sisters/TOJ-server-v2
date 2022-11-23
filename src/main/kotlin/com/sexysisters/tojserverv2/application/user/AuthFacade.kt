package com.sexysisters.tojserverv2.application.user

import com.sexysisters.tojserverv2.domain.notification.NotificationService
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.service.AuthService
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    private val authService: AuthService,
    private val notificationService: NotificationService,
) {

    fun googleLogin(userCommand: UserCommand.GoogleLoginRequest): UserInfo.TokenResponse {
        val response = authService.googleLogin(userCommand)
        notificationService.sendEmail("", "", "")
        return response
    }
}