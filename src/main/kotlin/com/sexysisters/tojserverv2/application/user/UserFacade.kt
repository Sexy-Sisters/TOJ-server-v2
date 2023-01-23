package com.sexysisters.tojserverv2.application.user

import com.sexysisters.tojserverv2.config.properties.MailProperties
import com.sexysisters.tojserverv2.domain.notification.NotificationService
import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.service.UserService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFacade(
    private val userService: UserService,
    private val notificationService: NotificationService,
) {
    fun createUser(command: UserCommand.CreateRequest): UUID {
        val userId = userService.createUser(command)
        notificationService.sendEmail(
            command.email,
            MailProperties.SIGNUP_TITLE,
            MailProperties.SIGNUP_DESCRIBTION,
        )
        return userId
    }
}