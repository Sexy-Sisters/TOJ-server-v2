package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.domain.user.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
): UserService {

    @Transactional
    override fun createUser(command: UserCommand.CreateRequest): Long {
        var initUser = command.toEntity()
        var savedUser = userStore.store(initUser)
        return savedUser.id!!
    }
}