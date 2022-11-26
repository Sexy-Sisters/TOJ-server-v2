package com.sexysisters.tojserverv2.domain.user.service

import com.sexysisters.tojserverv2.domain.user.UserCommand
import com.sexysisters.tojserverv2.domain.user.UserInfo
import com.sexysisters.tojserverv2.domain.user.UserMapper
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.domain.user.setEncodedPassword
import com.sexysisters.tojserverv2.domain.user.toEntity
import com.sexysisters.tojserverv2.domain.user.updateInfo
import com.sexysisters.tojserverv2.domain.user.updateProfileImg
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userStore: UserStore,
    private val userReader: UserReader,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    private val userMapper = Mappers.getMapper(UserMapper::class.java)

    @Transactional
    override fun createUser(command: UserCommand.CreateRequest): Long {
        command.setEncodedPassword(passwordEncoder)
        val initUser = command.toEntity()
        val savedUser = userStore.store(initUser)
        return savedUser.id!!
    }

    @Transactional(readOnly = true)
    override fun findUserProfile(userId: Long): UserInfo.Profile {
        val user = userReader.findUserById(userId)
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
        val updatedUser = user.updateInfo(command.nickname, command.name)
        return userMapper.of(updatedUser)
    }

    @Transactional
    override fun updateProfileImg(command: UserCommand.UpdateProfileImgRequest) {
        val user = userReader.getCurrentUser()
        val profileImg = command.profileImg
        user.updateProfileImg(profileImg)
    }
}