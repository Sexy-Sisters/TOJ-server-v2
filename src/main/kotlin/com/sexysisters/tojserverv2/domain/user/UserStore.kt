package com.sexysisters.tojserverv2.domain.user

interface UserStore {
    fun store(user: User): User
    fun storeOAuthUser(user: User)
}