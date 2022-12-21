package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.user.domain.User

interface UserReader {
    fun getUser(email: String): User
    fun getUser(id: Long): User
    fun getCurrentUser(): User
    fun existsUserByEmail(email: String): Boolean
}