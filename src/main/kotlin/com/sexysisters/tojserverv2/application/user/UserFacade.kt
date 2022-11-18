package com.sexysisters.tojserverv2.application.user

import com.sexysisters.tojserverv2.domain.notification.NotificationService
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.service.UserService
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userService: UserService,
    private val notificationService: NotificationService,
) {
    fun createUser(command: UserCommand.CreateRequest): Long {
        var userId = userService.createUser(command)
        notificationService.sendEmail(
            command.email,
            "TOJ 회원가입 완료",
            "TOJ 회원가입 완료"
        )
        return userId
    }
}
