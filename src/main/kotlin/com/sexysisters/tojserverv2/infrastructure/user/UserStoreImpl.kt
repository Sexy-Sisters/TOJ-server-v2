package com.sexysisters.tojserverv2.infrastructure.user

import com.sexysisters.tojserverv2.domain.user.User
import com.sexysisters.tojserverv2.domain.user.design.UserStore
import org.springframework.stereotype.Component

@Component
class UserStoreImpl (
    private val userRepository: UserRepository,
): UserStore {

    override fun store(user: User) = userRepository.save(user)
}