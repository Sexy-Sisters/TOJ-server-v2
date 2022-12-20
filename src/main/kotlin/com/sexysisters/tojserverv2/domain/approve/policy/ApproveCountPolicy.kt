package com.sexysisters.tojserverv2.domain.approve.policy

import com.sexysisters.tojserverv2.domain.student.domain.Student
import com.sexysisters.tojserverv2.domain.student.domain.engaged
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/*
    N명의 학생중 N/5 명의 학생이
    참가 신청을 수락하면 신청한 학생이 소속된다.
 */
@Order(value = 30)
@Component
class ApproveCountPolicy : ApprovePolicy {

    override fun check(applicant: Student, acceptor: Student) {
        val school = acceptor.school!!
        val studentCapacity = school.students.size
        val neededCount = studentCapacity / 5
        val approveCount = applicant.approves.size
        val isOver = approveCount >= neededCount
        if (isOver) {
            applicant.engaged()
        }
    }
}