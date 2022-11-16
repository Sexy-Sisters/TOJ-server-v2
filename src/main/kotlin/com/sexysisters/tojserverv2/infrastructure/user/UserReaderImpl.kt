package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import org.springframework.stereotype.Component

@Component
class UserReaderImpl(
    private val userRepository: UserRepository,
): UserReader {

    override fun findUserByEmail(email: String): User {
        return userRepository.findByEmail(email)
            ?: throw RuntimeException()
    }
}