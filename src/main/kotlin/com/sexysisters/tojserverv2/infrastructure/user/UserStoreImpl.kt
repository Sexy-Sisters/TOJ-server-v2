package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.domain.User
import com.sexysisters.tojserverv2.domain.user.UserStore
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(
    private val userRepository: UserRepository,
) : UserStore {

    override fun store(user: User): User {
        validation(user.emailValue(), user.nicknameValue())
        return userRepository.save(user)
    }

    private fun validation(email: String, nickname: String) {
        if (userRepository.existsByEmailValue(email)) throw UserException.EmailAlreadyExists()
        if (userRepository.existsByNicknameValue(nickname)) throw UserException.NicknameAlreadyExists()
    }

    override fun storeOAuthUser(user: User) {
        if (!isAlreadyExists(user)) {
            store(user)
        }
    }

    private fun isAlreadyExists(user: User) = userRepository.existsByEmailValue(user.emailValue())
}