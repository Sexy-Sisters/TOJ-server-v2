package com.sexysisters.tojserverv2.domain.student.policy

import com.sexysisters.tojserverv2.domain.user.domain.User

interface StudentPolicy {
    fun check(user: User)
}