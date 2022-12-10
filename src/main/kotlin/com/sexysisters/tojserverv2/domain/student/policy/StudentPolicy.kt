package com.sexysisters.tojserverv2.domain.student.policy

import com.sexysisters.tojserverv2.domain.user.User

interface StudentPolicy {
    fun check(user: User)
}