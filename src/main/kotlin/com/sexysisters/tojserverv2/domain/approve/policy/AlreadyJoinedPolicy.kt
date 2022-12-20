package com.sexysisters.tojserverv2.domain.approve.policy

import com.sexysisters.tojserverv2.domain.approve.exception.ApproveException
import com.sexysisters.tojserverv2.domain.student.domain.Status
import com.sexysisters.tojserverv2.domain.student.domain.Student
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    이미 다른 학교에 소속된
    학생은 소속될 수 없다.
 */
@Order(10)
@Component
class AlreadyJoinedPolicy : ApprovePolicy {

    override fun check(applicant: Student, acceptor: Student) {
        val isAlreadyJoined = applicant.status == Status.ENGAGED
        if (isAlreadyJoined) {
            throw ApproveException.AlreadyJoined()
        }
    }
}