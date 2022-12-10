package com.sexysisters.tojserverv2.domain.school.policy

import com.sexysisters.tojserverv2.domain.school.School
import com.sexysisters.tojserverv2.domain.school.exception.SchoolException
import com.sexysisters.tojserverv2.domain.student.Student
import com.sexysisters.tojserverv2.domain.student.exception.StudentException
import com.sexysisters.tojserverv2.domain.student.policy.StudentPolicy
import com.sexysisters.tojserverv2.domain.user.User
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


/*
    이미 가입했다면 가입 불가
 */
@Order(10)
@Component
class AlreadyJoinedPolicy : SchoolPolicy {

    override fun check(school: School, student: Student) {
        if (student.school != null) {
            throw SchoolException.AlreadyJoined()
        }
    }
}