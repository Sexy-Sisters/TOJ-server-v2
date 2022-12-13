package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserStore
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(
    private val userRepository: UserRepository,
) : UserStore {

    override fun store(user: User): User {
        validation(user.email, user.nickname)
        return userRepository.save(user)
    }

    private fun validation(email: String, nickname: String) {
        if (userRepository.existsByEmail(email)) throw UserException.EmailAlreadyExists()
        if (userRepository.existsByNickname(nickname)) throw UserException.NicknameAlreadyExists()
    }

    override fun storeOAuthUser(user: User) {
        if (!isAlreadyExists(user)) {
            store(user)
        }
    }

    private fun isAlreadyExists(user: User) = userRepository.existsByEmail(user.email)
}