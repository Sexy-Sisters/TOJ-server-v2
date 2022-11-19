package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.domain.user.setEncodedPassword
import com.sexysisters.tojserverv2.domain.user.toEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userReader: UserReader,
    private val passwordEncoder: PasswordEncoder,
) : UserService {

    @Transactional
    override fun createUser(command: UserCommand.CreateRequest): Long {
        command.setEncodedPassword(passwordEncoder)
        val initUser = command.toEntity()
        val savedUser = userStore.store(initUser)
        return savedUser.id!!
    }

    @Transactional
    override fun findUserProfile(userId: Long): UserInfo.Profile {
        val user = userReader.findUserById(userId)
        return UserInfo.Profile.of(user)
    }
}