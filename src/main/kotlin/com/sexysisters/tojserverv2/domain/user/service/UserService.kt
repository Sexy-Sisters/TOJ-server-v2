package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand

interface UserService {
    fun createUser(command: UserCommand.CreateRequest): Long
}