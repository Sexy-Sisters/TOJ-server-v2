package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.UserMapper
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.UserStore
import com.sexysisters.tojserverv2.domain.user.domain.*
import com.sexysisters.tojserverv2.domain.user.setEncodedPassword
import com.sexysisters.tojserverv2.domain.user.toEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userReader: UserReader,
    private val passwordEncoder: PasswordEncoder,
    private val userMapper: UserMapper,
) : UserService {

    @Transactional
    override fun createUser(command: UserCommand.CreateRequest): UUID {
        command.setEncodedPassword(passwordEncoder)
        val initUser = command.toEntity()
        val savedUser = userStore.store(initUser)
        return savedUser.id
    }

    @Transactional(readOnly = true)
    override fun findUserProfile(userId: String): UserInfo.Profile {
        val user = userReader.getUser(userId)
        return userMapper.of(user)
    }

    @Transactional(readOnly = true)
    override fun findCurrentUserProfile(): UserInfo.Profile {
        val user = userReader.getCurrentUser()
        return userMapper.of(user)
    }

    @Transactional
    override fun updateUser(command: UserCommand.UpdateRequest): UserInfo.Profile {
        val user = userReader.getCurrentUser()
        val updatedUser = user.updateInfo(
            nickname = Nickname(command.nickname),
            name = Name(command.name)
        )
        return userMapper.of(updatedUser)
    }

    @Transactional
    override fun updateProfileImg(command: UserCommand.UpdateProfileImgRequest) {
        val user = userReader.getCurrentUser()
        user.updateProfileImg(Image(command.profileImg))
    }
}