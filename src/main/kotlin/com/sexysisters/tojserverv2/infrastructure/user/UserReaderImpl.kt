package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.common.security.auth.AuthDetails
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserReaderImpl(
    private val userRepository: UserRepository,
) : UserReader {

    override fun findUserByEmail(email: String): User {
        return userRepository.findByEmail(email)
            ?: throw UserException.EmailAlreadyExists()
    }

    override fun findUserById(id: Long): User {
        return userRepository.findByIdOrNull(id)
            ?: throw UserException.UserNotFound()
    }

    override fun getCurrentUser(): User {
        val auth = SecurityContextHolder.getContext().authentication.principal as AuthDetails
        return findUserByEmail(auth.username)
    }
}