package com.sexysisters.tojserverv2.common.security.auth

import com.sexysisters.tojserverv2.domain.user.design.UserReader
import com.sexysisters.tojserverv2.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userReader: UserReader,
) : UserDetailsService {

    override fun loadUserByUsername(username: String) =
        AuthDetails(userReader.findUserByEmail(username))
}