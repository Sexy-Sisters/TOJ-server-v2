package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.user.domain.User
import java.util.UUID

interface UserReader {
    fun getUser(email: String): User
    fun getUser(id: UUID): User
    fun getCurrentUser(): User
    fun existsUserByEmail(email: String): Boolean
}