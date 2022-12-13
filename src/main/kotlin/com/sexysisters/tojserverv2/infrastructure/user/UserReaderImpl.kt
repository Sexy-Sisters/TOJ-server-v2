package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.common.security.auth.AuthDetails
import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.UserException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserReaderImpl(
    private val userRepository: UserRepository,
) : UserReader {

    override fun getUser(email: String) =
        userRepository.findByEmail(email)
            ?: throw UserException.UserNotFound()

    override fun getUser(id: Long) =
        userRepository.findByIdOrNull(id)
            ?: throw UserException.UserNotFound()

    override fun getCurrentUser(): User {
        val auth = SecurityContextHolder.getContext().authentication.principal as AuthDetails
        return getUser(auth.username)
    }

    override fun existsUserByEmail(email: String) = userRepository.existsByEmail(email)
}