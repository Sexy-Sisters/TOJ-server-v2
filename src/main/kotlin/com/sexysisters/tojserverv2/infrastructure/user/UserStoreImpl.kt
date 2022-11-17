package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import com.sexysisters.tojserverv2.domain.user.exception.EmailAlreadyExistsException
import com.sexysisters.tojserverv2.domain.user.exception.NicknameAlreadyExistsException
import org.springframework.stereotype.Component

@Component
class UserStoreImpl (
    private val userRepository: UserRepository,
): UserStore {

    override fun store(user: User): User {
        validation(user.email, user.nickname)
        return userRepository.save(user)
    }

    private fun validation(email: String, nickname: String) {
        if (userRepository.existsByEmail(email)) throw EmailAlreadyExistsException()
        if (userRepository.existsByNickname(nickname)) throw NicknameAlreadyExistsException()
    }
}