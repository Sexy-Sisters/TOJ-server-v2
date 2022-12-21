package com.sexysisters.tojserverv2.domain.user

import com.sexysisters.tojserverv2.domain.user.domain.User

interface UserStore {
    fun store(user: User): User
    fun storeOAuthUser(user: User)
}