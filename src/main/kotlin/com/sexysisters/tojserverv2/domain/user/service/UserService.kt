package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import java.util.*

interface UserService {
    fun createUser(command: UserCommand.CreateRequest): UUID
    fun findUserProfile(userId: String): UserInfo.Profile
    fun findCurrentUserProfile(): UserInfo.Profile
    fun updateUser(command: UserCommand.UpdateRequest): UserInfo.Profile
    fun updateProfileImg(command: UserCommand.UpdateProfileImgRequest)
}