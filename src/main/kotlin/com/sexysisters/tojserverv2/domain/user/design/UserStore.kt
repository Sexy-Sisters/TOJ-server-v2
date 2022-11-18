package com.sexysisters.tojserverv2.domain.user.design

import com.sexysisters.tojserverv2.domain.user.User

interface UserStore {
    fun store(user: User): User
}
