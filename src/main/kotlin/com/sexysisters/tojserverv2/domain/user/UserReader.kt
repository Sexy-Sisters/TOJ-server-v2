package com.sexysisters.tojserverv2.domain.user

interface UserReader {
    fun getUser(email: String): User
    fun getUser(id: Long): User
    fun getCurrentUser(): User
    fun existsUserByEmail(email: String): Boolean
}