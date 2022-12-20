package com.sexysisters.tojserverv2.domain.student.policy

import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.user.User
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    학생 인적사항은 한 유저당 하나만 존재할 수 있다.
 */
@Order(10)
@Component
class AlreadyCreatedPolicy : StudentPolicy {

    override fun check(user: User) {
        val hasStudent = user.student != null
        if (hasStudent) {
            throw StudentException.AlreadyCreated()
        }
    }
}