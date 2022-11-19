package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo

interface UserService {
    fun createUser(command: UserCommand.CreateRequest): Long
    fun findUserProfile(userId: Long): UserInfo.Profile
}