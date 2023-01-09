package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmailValue(email: String): User?
    fun existsByEmailValue(email: String): Boolean
    fun existsByNicknameValue(nickname: String): Boolean
}